package com.organizamoney.backend.springmonolith.domain.shared.entities.valueObjects;

public class Email extends ValueObject<String> {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private Email(String value) {
        super(value);
    }

    public static Email create(String value) {
        return new Email(value);
    }

    @Override
    public void validate(String value) {
        if (value == null || !value.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Invalid email");
        }
    }
}
