package com.organizamoney.backend.springmonolith.domain.account.application.exceptions;

import com.organizamoney.backend.springmonolith.domain.shared.exceptions.DomainException;

public class FailOnRegisterException extends DomainException {
    public FailOnRegisterException() {
        super("Fail on register user.");
    }
}
