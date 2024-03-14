package com.organizamoney.backend.springmonolith.adapter.account.data.springjpa.repositories;

import com.organizamoney.backend.springmonolith.adapter.account.data.springjpa.mapper.AccountMapper;
import com.organizamoney.backend.springmonolith.domain.account.application.repositories.AccountRepository;
import com.organizamoney.backend.springmonolith.domain.account.enterprise.entities.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;

    @Override
    public Boolean existsByEmail(String email) {
        return this.accountJpaRepository.existsByEmail(email);
    }

    @Override
    public Account create(Account account) {
        this.accountJpaRepository.save(AccountMapper.toJpa(account));
        return account;
    }
}
