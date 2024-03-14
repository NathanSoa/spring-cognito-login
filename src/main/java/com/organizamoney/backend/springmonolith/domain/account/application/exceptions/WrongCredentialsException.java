package com.organizamoney.backend.springmonolith.domain.account.application.exceptions;

import com.organizamoney.backend.springmonolith.domain.shared.exceptions.DomainException;

public class WrongCredentialsException extends DomainException {
    public WrongCredentialsException(String identifier) {
        super("Account " + identifier + " not found.");
    }
}
