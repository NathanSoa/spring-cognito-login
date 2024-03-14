package com.organizamoney.backend.springmonolith.domain.account.application.useCases.io.signUp;

public record SignUpUseCaseIn(String name, String email, String password, String confirmPassword) {
}
