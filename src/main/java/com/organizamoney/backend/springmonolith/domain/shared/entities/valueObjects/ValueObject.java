package com.organizamoney.backend.springmonolith.domain.shared.entities.valueObjects;

public abstract class ValueObject<T> {

    private final T value;

    public abstract void validate(T value);

    public T getValue() {
        return value;
    }

    public ValueObject(T value) {
        validate(value);
        this.value = value;
    }
}
