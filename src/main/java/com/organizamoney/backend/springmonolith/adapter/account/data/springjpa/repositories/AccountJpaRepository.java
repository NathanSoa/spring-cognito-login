package com.organizamoney.backend.springmonolith.adapter.account.data.springjpa.repositories;

import com.organizamoney.backend.springmonolith.adapter.account.data.springjpa.entities.AccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountJpaEntity, Long> {
    Boolean existsByEmail(String email);
}
