package com.organizamoney.backend.springmonolith.configuration.aws.cognito;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

public class CognitoClient {

    private static CognitoIdentityProviderClient cognitoClient;

    public static CognitoIdentityProviderClient get() {

        if(cognitoClient == null) {
            cognitoClient = CognitoIdentityProviderClient.builder()
                    .region(Region.US_EAST_1)
                    .build();
        }

        return cognitoClient;
    }
}
