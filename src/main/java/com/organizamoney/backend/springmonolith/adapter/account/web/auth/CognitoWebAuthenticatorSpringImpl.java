package com.organizamoney.backend.springmonolith.adapter.account.web.auth;

import com.organizamoney.backend.springmonolith.configuration.aws.cognito.CognitoClient;
import com.organizamoney.backend.springmonolith.domain.account.application.auth.WebAuthenticator;
import com.organizamoney.backend.springmonolith.domain.account.application.auth.io.RegisterIn;
import com.organizamoney.backend.springmonolith.domain.account.application.exceptions.FailOnRegisterException;
import com.organizamoney.backend.springmonolith.domain.account.application.exceptions.WrongCredentialsException;
import com.organizamoney.backend.springmonolith.domain.account.application.useCases.io.signIn.SignInUseCaseIn;
import com.organizamoney.backend.springmonolith.domain.shared.logic.Either;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.InitiateAuthRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static com.organizamoney.backend.springmonolith.domain.shared.logic.Either.left;
import static com.organizamoney.backend.springmonolith.domain.shared.logic.Either.right;

@Component
public class CognitoWebAuthenticatorSpringImpl implements WebAuthenticator {

    private final CognitoIdentityProviderClient cognitoClient;

    @Value("${cognito.user-pool-id}")
    public String USER_POOL_ID;

    @Value("${cognito.client-id}")
    public String CLIENT_ID;

    @Value("${cognito.client-secret}")
    public String CLIENT_SECRET;

    public CognitoWebAuthenticatorSpringImpl() {
        this.cognitoClient = CognitoClient.get();
    }

    @Override
    public Either<WrongCredentialsException, String> authenticate(SignInUseCaseIn input) {
        final var secretHash = this.calculateSecretHash(this.CLIENT_ID, this.CLIENT_SECRET, input.email());
        final var request = InitiateAuthRequest.builder()
                .authFlow("USER_PASSWORD_AUTH")
                .clientId(this.CLIENT_ID)
                .authParameters(
                        Map.of(
                                "USERNAME", input.email(),
                                "PASSWORD", input.password(),
                                "SECRET_HASH", secretHash
                        )
                ).build();
        final var response = this.cognitoClient.initiateAuth(request).authenticationResult();

        if (response != null) {
            return right(response.accessToken());
        }

        return left(new WrongCredentialsException(input.email()));
    }

    @Override
    public Either<FailOnRegisterException, Boolean> register(RegisterIn input) {
        final var emailAttribute = this.createAttribute(input.email(), "email");
        final var attributes = List.of(emailAttribute);

        final var request = SignUpRequest.builder().userAttributes(attributes)
                .username(input.email())
                .password(input.password())
                .clientId(this.CLIENT_ID)
                .secretHash(this.calculateSecretHash(this.CLIENT_ID, this.CLIENT_SECRET, input.email()))
                .build();

        final var response = this.cognitoClient.signUp(request);

        if (response != null) {
            return right(true);
        }

        return left(new FailOnRegisterException());
    }

    public String calculateSecretHash(String userPoolClientId, String userPoolClientSecret, String userName) {
        final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

        SecretKeySpec signingKey = new SecretKeySpec(
                userPoolClientSecret.getBytes(StandardCharsets.UTF_8),
                HMAC_SHA256_ALGORITHM);
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            mac.update(userName.getBytes(StandardCharsets.UTF_8));
            byte[] rawHmac = mac.doFinal(userPoolClientId.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating ");
        }
    }

    public AttributeType createAttribute(String value, String name) {
        return AttributeType.builder()
                .name(name)
                .value(value)
                .build();
    }
}
