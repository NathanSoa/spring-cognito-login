package com.organizamoney.backend.springmonolith.domain.account.application.auth;

import com.organizamoney.backend.springmonolith.domain.account.application.auth.io.RegisterIn;
import com.organizamoney.backend.springmonolith.domain.account.application.exceptions.FailOnRegisterException;
import com.organizamoney.backend.springmonolith.domain.account.application.exceptions.WrongCredentialsException;
import com.organizamoney.backend.springmonolith.domain.account.application.useCases.io.signIn.SignInUseCaseIn;
import com.organizamoney.backend.springmonolith.domain.shared.logic.Either;

public interface WebAuthenticator {
    public Either<WrongCredentialsException, String> authenticate(SignInUseCaseIn input);
    public Either<FailOnRegisterException, Boolean> register(RegisterIn input);
}
