package com.organizamoney.backend.springmonolith.adapter.account.web.useCases;

import com.organizamoney.backend.springmonolith.adapter.shared.cryptography.Hasher;
import com.organizamoney.backend.springmonolith.domain.account.application.auth.WebAuthenticator;
import com.organizamoney.backend.springmonolith.domain.account.application.auth.io.RegisterIn;
import com.organizamoney.backend.springmonolith.domain.account.application.repositories.AccountRepository;
import com.organizamoney.backend.springmonolith.domain.account.application.useCases.SignUpUseCase;
import com.organizamoney.backend.springmonolith.domain.account.application.useCases.io.signUp.SignUpUseCaseIn;
import com.organizamoney.backend.springmonolith.domain.account.enterprise.entities.Account;
import com.organizamoney.backend.springmonolith.domain.account.enterprise.policies.SignUpPolicy;
import com.organizamoney.backend.springmonolith.domain.shared.exceptions.DomainException;
import com.organizamoney.backend.springmonolith.domain.shared.logic.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.organizamoney.backend.springmonolith.domain.shared.logic.Either.left;
import static com.organizamoney.backend.springmonolith.domain.shared.logic.Either.right;

@Service
@AllArgsConstructor
public class SignUpUseCaseSpring implements SignUpUseCase {

    public SignUpPolicy signUpPolicy;
    public AccountRepository accountRepository;
    public WebAuthenticator webAuthenticator;
    public Hasher hasher;

    @Override
    public Either<DomainException, Boolean> execute(SignUpUseCaseIn input) {
        final var policyResult = this.signUpPolicy.verifyPassword(input.password(), input.confirmPassword());

        if (policyResult.isLeft()) {
            return left(policyResult.getLeft());
        }

        final var authResult = this.webAuthenticator.register(new RegisterIn(
                input.email(),
                input.password()
        ));

        if (authResult.isLeft()) {
            return left(authResult.getLeft());
        }

        final var account = this.createAccount(input);
        this.accountRepository.create(account);
        return right(true);
    }

    private Account createAccount(SignUpUseCaseIn input) {
        return Account.create(input.name(), input.email(), this.hasher.hash(input.password()));
    }
}
