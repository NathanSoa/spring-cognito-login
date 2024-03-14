package com.organizamoney.backend.springmonolith.adapter.account.web.service;

import com.organizamoney.backend.springmonolith.adapter.account.web.requestresponse.signIn.SignInReq;
import com.organizamoney.backend.springmonolith.adapter.account.web.requestresponse.signIn.SignInRes;
import com.organizamoney.backend.springmonolith.adapter.account.web.requestresponse.signUp.SignUpReq;
import com.organizamoney.backend.springmonolith.domain.account.application.exceptions.WrongCredentialsException;
import com.organizamoney.backend.springmonolith.domain.account.application.useCases.SignInUseCase;
import com.organizamoney.backend.springmonolith.domain.account.application.useCases.SignUpUseCase;
import com.organizamoney.backend.springmonolith.domain.account.application.useCases.io.signIn.SignInUseCaseIn;
import com.organizamoney.backend.springmonolith.domain.account.application.useCases.io.signUp.SignUpUseCaseIn;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountWebServiceSpring {

    private final SignInUseCase signInUseCase;
    private final SignUpUseCase signUpUseCase;

    public SignInRes signIn(SignInReq request) {
        final var response = signInUseCase.execute(
                new SignInUseCaseIn(
                        request.email(),
                        request.password()
                )
        );

        if (response.isLeft()) {
            throw new WrongCredentialsException(response.getLeft().getMessage());
        }

        return new SignInRes(response.getRight().token());
    }

    public void signup(SignUpReq request) {
        final var response = signUpUseCase.execute(
                new SignUpUseCaseIn(
                        request.name(),
                        request.email(),
                        request.password(),
                        request.confirmPassword()
                )
        );

        if (response.isLeft()) {
            throw response.getLeft();
        }
    }
}
