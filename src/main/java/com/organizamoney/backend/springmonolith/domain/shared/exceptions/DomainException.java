package com.organizamoney.backend.springmonolith.domain.shared.exceptions;

public abstract class DomainException extends RuntimeException {
    public DomainException(String s) {
        super(s);
    }
}
