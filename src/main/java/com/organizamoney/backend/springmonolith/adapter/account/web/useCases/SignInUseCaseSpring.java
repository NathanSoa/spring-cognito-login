package com.organizamoney.backend.springmonolith.adapter.account.web.useCases;

import com.organizamoney.backend.springmonolith.domain.account.application.auth.WebAuthenticator;
import com.organizamoney.backend.springmonolith.domain.account.application.exceptions.WrongCredentialsException;
import com.organizamoney.backend.springmonolith.domain.account.application.repositories.AccountRepository;
import com.organizamoney.backend.springmonolith.domain.account.application.useCases.SignInUseCase;
import com.organizamoney.backend.springmonolith.domain.account.application.useCases.io.signIn.SignInUseCaseIn;
import com.organizamoney.backend.springmonolith.domain.account.application.useCases.io.signIn.SignInUseCaseOut;
import com.organizamoney.backend.springmonolith.domain.shared.exceptions.DomainException;
import com.organizamoney.backend.springmonolith.domain.shared.logic.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.organizamoney.backend.springmonolith.domain.shared.logic.Either.left;
import static com.organizamoney.backend.springmonolith.domain.shared.logic.Either.right;

@Service
@AllArgsConstructor
public class SignInUseCaseSpring implements SignInUseCase {

    private AccountRepository accountRepository;
    private WebAuthenticator webAuthenticator;

    @Override
    public Either<DomainException, SignInUseCaseOut> execute(SignInUseCaseIn input) {
        final var email = input.email();
        final var password = input.password();

        final var verifyEmailExistence = accountRepository.existsByEmail(email);

        if(this.doesNotExists(verifyEmailExistence)) {
            return left(new WrongCredentialsException(email));
        }

        final var result = this.webAuthenticator.authenticate(
                new SignInUseCaseIn(
                        email,
                        password
                )
        );

        if(result.isLeft()) {
            return left(new WrongCredentialsException(email));
        }
        return right(new SignInUseCaseOut(result.getRight()));
    }

    private boolean doesNotExists(Boolean verifyEmailExistence) {
        return !verifyEmailExistence;
    }
}
