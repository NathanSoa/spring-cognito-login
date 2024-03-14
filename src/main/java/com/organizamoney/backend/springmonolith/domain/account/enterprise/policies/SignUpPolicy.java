package com.organizamoney.backend.springmonolith.domain.account.enterprise.policies;

import com.organizamoney.backend.springmonolith.domain.account.enterprise.exceptions.PasswordDoesNotMatchException;
import com.organizamoney.backend.springmonolith.domain.shared.exceptions.DomainException;
import com.organizamoney.backend.springmonolith.domain.shared.logic.Either;

import static com.organizamoney.backend.springmonolith.domain.shared.logic.Either.left;
import static com.organizamoney.backend.springmonolith.domain.shared.logic.Either.right;

public class SignUpPolicy {

    public Either<DomainException, Boolean> verifyPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return left(new PasswordDoesNotMatchException());
        }
        return right(true);
    }
}
