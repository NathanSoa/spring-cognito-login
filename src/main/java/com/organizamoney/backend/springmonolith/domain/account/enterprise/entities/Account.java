package com.organizamoney.backend.springmonolith.domain.account.enterprise.entities;

import com.organizamoney.backend.springmonolith.domain.shared.entities.Entity;
import com.organizamoney.backend.springmonolith.domain.shared.entities.valueObjects.Email;

public class Account extends Entity {

    private String name;
    private Email email;
    private String password;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email.getValue();
    }

    public String getPassword() {
        return password;
    }

    private Account() {
        super();
    }

    public static Account create(String name, String email, String password) {
        Account account = new Account();
        account.name = name;
        account.email = Email.create(email);
        account.password = password;
        return account;
    }
}
