package com.organizamoney.backend.springmonolith.domain.account.enterprise.exceptions;

import com.organizamoney.backend.springmonolith.domain.shared.exceptions.DomainException;

public class PasswordDoesNotMatchException extends DomainException {
    public PasswordDoesNotMatchException() {
        super("Password does not match!");
    }
}
