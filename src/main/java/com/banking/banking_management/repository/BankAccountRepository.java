package com.banking.banking_management.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.banking_management.entity.BankAccount;

public interface BankAccountRepository
        extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findByAccountNumber(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);
}
