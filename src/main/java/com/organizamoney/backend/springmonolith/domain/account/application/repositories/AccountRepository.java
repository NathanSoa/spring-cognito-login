package com.organizamoney.backend.springmonolith.domain.account.application.repositories;

import com.organizamoney.backend.springmonolith.domain.account.enterprise.entities.Account;

import java.util.Optional;

public interface AccountRepository {

    Boolean existsByEmail(String email);
    Account create(Account account);
}
