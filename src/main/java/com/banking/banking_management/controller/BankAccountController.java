package com.banking.banking_management.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.banking.banking_management.entity.BankAccount;
import com.banking.banking_management.service.BankAccountService;
import com.banking.banking_management.entity.BankTransaction;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    private final BankAccountService service;

    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    // Create a bank account
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccount createAccount(@RequestBody BankAccount account) {
        return service.createAccount(account);
    }

    // Get all bank accounts
    @GetMapping
    public List<BankAccount> getAllAccounts() {
        return service.getAllAccounts();
    }

    // Get account by ID
    @GetMapping("/{id}")
    public BankAccount getAccount(@PathVariable Long id) {
        return service.getAccount(id);
    }

    // Deposit money
    @PostMapping("/{id}/deposit")
    public BankAccount deposit(
            @PathVariable Long id,
            @RequestBody Map<String, BigDecimal> request) {

        return service.deposit(id, request.get("amount"));
    }

    // Withdraw money
    @PostMapping("/{id}/withdraw")
    public BankAccount withdraw(
            @PathVariable Long id,
            @RequestBody Map<String, BigDecimal> request) {

        return service.withdraw(id, request.get("amount"));
    }

    // Transfer money
    @PostMapping("/transfer")
    public Map<String, String> transfer(
            @RequestBody Map<String, String> request) {

        Long fromId = Long.valueOf(request.get("fromId"));
        Long toId = Long.valueOf(request.get("toId"));
        BigDecimal amount = new BigDecimal(request.get("amount"));

        service.transfer(fromId, toId, amount);

        return Map.of("message", "Money transferred successfully");
    }
 // Get transaction history for an account
    @GetMapping("/{id}/transactions")
    public List<BankTransaction> getTransactionHistory(@PathVariable Long id) {
        return service.getTransactionHistory(id);
    }
}

