package com.banking.banking_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.banking_management.entity.BankTransaction;

public interface BankTransactionRepository
        extends JpaRepository<BankTransaction, Long> {

    List<BankTransaction> findByAccountIdOrderByTransactionDateDesc(Long accountId);
}