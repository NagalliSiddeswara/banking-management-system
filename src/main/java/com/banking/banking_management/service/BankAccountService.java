package com.banking.banking_management.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banking.banking_management.entity.BankAccount;
import com.banking.banking_management.entity.BankTransaction;
import com.banking.banking_management.repository.BankAccountRepository;
import com.banking.banking_management.repository.BankTransactionRepository;

@Service
public class BankAccountService {

    private final BankAccountRepository repository;
    private final BankTransactionRepository transactionRepository;

    public BankAccountService(
            BankAccountRepository repository,
            BankTransactionRepository transactionRepository) {

        this.repository = repository;
        this.transactionRepository = transactionRepository;
    }

    // Create a new bank account
    @Transactional
    public BankAccount createAccount(BankAccount account) {

        if (account.getAccountHolderName() == null
                || account.getAccountHolderName().isBlank()) {
            throw new IllegalArgumentException(
                    "Account holder name is required");
        }

        if (account.getAccountNumber() == null
                || account.getAccountNumber().isBlank()) {
            throw new IllegalArgumentException(
                    "Account number is required");
        }

        if (repository.existsByAccountNumber(account.getAccountNumber())) {
            throw new IllegalArgumentException(
                    "Account number already exists");
        }

        account.setId(null);
        account.setBalance(BigDecimal.ZERO);

        return repository.save(account);
    }

    // Get all bank accounts
    public List<BankAccount> getAllAccounts() {
        return repository.findAll();
    }

    // Get account by ID
    public BankAccount getAccount(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Account not found"));
    }

    // Deposit money
    @Transactional
    public BankAccount deposit(Long id, BigDecimal amount) {

        validateAmount(amount);

        BankAccount account = getAccount(id);

        account.setBalance(account.getBalance().add(amount));

        BankAccount savedAccount = repository.save(account);

        transactionRepository.save(
                new BankTransaction(
                        id,
                        "DEPOSIT",
                        amount,
                        null
                )
        );

        return savedAccount;
    }

    // Withdraw money
    @Transactional
    public BankAccount withdraw(Long id, BigDecimal amount) {

        validateAmount(amount);

        BankAccount account = getAccount(id);

        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException(
                    "Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(amount));

        BankAccount savedAccount = repository.save(account);

        transactionRepository.save(
                new BankTransaction(
                        id,
                        "WITHDRAWAL",
                        amount,
                        null
                )
        );

        return savedAccount;
    }

    // Transfer money between accounts
    @Transactional
    public void transfer(Long fromId, Long toId, BigDecimal amount) {

        validateAmount(amount);

        if (fromId.equals(toId)) {
            throw new IllegalArgumentException(
                    "Cannot transfer money to the same account");
        }

        BankAccount sender = getAccount(fromId);
        BankAccount receiver = getAccount(toId);

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException(
                    "Insufficient balance");
        }

        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));

        repository.save(sender);
        repository.save(receiver);

        // Record sender's transaction
        transactionRepository.save(
                new BankTransaction(
                        fromId,
                        "TRANSFER_OUT",
                        amount,
                        toId
                )
        );

        // Record receiver's transaction
        transactionRepository.save(
                new BankTransaction(
                        toId,
                        "TRANSFER_IN",
                        amount,
                        fromId
                )
        );
    }

    // Get transaction history
    public List<BankTransaction> getTransactionHistory(Long accountId) {

        getAccount(accountId);

        return transactionRepository
                .findByAccountIdOrderByTransactionDateDesc(accountId);
    }

    // Validate transaction amount
    private void validateAmount(BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero");
        }

        if (amount.scale() > 2) {
            throw new IllegalArgumentException(
                    "Amount cannot have more than two decimal places");
        }
    }
}

