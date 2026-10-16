package com.banking.banking_management.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank_transactions")
public class BankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    private String transactionType;

    private BigDecimal amount;

    private Long relatedAccountId;

    private LocalDateTime transactionDate;

    public BankTransaction() {
    }

    public BankTransaction(Long accountId, String transactionType,
                           BigDecimal amount, Long relatedAccountId) {
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.relatedAccountId = relatedAccountId;
        this.transactionDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getRelatedAccountId() {
        return relatedAccountId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
}