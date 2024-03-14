package com.organizamoney.backend.springmonolith.domain.account.application.useCases;

import com.organizamoney.backend.springmonolith.domain.account.application.useCases.io.signUp.SignUpUseCaseIn;
import com.organizamoney.backend.springmonolith.domain.shared.useCases.UseCase;

public interface SignUpUseCase extends UseCase<SignUpUseCaseIn, Boolean> {
}
