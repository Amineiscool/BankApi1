package com.BankApi.BankApi.controller;

import com.BankApi.BankApi.errorException.exception.ResourceNotFoundException;
import com.BankApi.BankApi.model.Account;
import com.BankApi.BankApi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
        try {
            Account account = accountService.getAccountById(accountId);
            return ResponseEntity.ok(account);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/accounts/customers/{customerId}/accounts")
    public ResponseEntity<?> createAccount(@RequestBody Account accountInfo, @PathVariable Long customerId) {
        try {
            Account createdAccount = accountService.createAccount(accountInfo, customerId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long accountId, @RequestBody Account accountInfo) {
        try {
            Account updatedAccount = accountService.updateAccount(accountId, accountInfo);
            return ResponseEntity.ok(updatedAccount);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/customers/{customerId}/accounts")
//    public ResponseEntity<?> getAllAccountsByCustomerId(@PathVariable Long customerId) {
//        try {
//            List<Account> accounts = accountService.getAccountsByCustomerId(customerId);
//            return ResponseEntity.ok(accounts);
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
}
