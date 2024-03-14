package com.organizamoney.backend.springmonolith.domain.account.application.useCases;

import com.organizamoney.backend.springmonolith.domain.account.application.useCases.io.signIn.SignInUseCaseIn;
import com.organizamoney.backend.springmonolith.domain.account.application.useCases.io.signIn.SignInUseCaseOut;
import com.organizamoney.backend.springmonolith.domain.shared.useCases.UseCase;

public interface SignInUseCase extends UseCase<SignInUseCaseIn, SignInUseCaseOut> {
}
