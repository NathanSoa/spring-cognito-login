package com.organizamoney.backend.springmonolith.adapter.account.data.springjpa.mapper;

import com.organizamoney.backend.springmonolith.adapter.account.data.springjpa.entities.AccountJpaEntity;
import com.organizamoney.backend.springmonolith.domain.account.enterprise.entities.Account;

public class AccountMapper {
    public static AccountJpaEntity toJpa(Account raw) {
        final var account = new AccountJpaEntity();
        account.setId(raw.getId());
        account.setName(raw.getName());
        account.setEmail(raw.getEmail());
        account.setPassword(raw.getPassword());
        return account;
    }
}
