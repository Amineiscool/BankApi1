package com.BankApi.BankApi.controller;

import com.BankApi.BankApi.model.Account;
import com.BankApi.BankApi.reply.CustomReply;
import com.BankApi.BankApi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        //int code = HttpStatus.OK.value();
        // String massage = "Success";
        List<Account> accounts = (List<Account>) accountService.getAllAccounts();

        //return ResponseEntity.ok(accounts);
        return (new ResponseEntity<>(accounts, HttpStatus.OK));
        }

   /* @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId, @RequestBody String exceptionMessage) {
        Account account = accountService.getAccountById(accountId, exceptionMessage);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
        String exceptionMessage = "error fetching account";
        //int CustomerReply = HttpStatus.OK.value();
        Account responseData = this.accountService.getAccountById(accountId, exceptionMessage);
        if (responseData != null) {
        return ResponseEntity.ok(responseData);
        } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage);
        }
        }


    @PostMapping("/customers/{customerID}/accounts")
    public ResponseEntity<?> createAccount(@RequestBody Account accountInfo, @PathVariable Long customerId) {
        String message = "error fetching creating customers account";
        int code = HttpStatus.CREATED.value();
        Account createdAccount = this.accountService.createAccount(accountInfo, customerId, code, message);
        return (new ResponseEntity<>(createdAccount, HttpStatus.CREATED));
        }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        Account updatedAccount = accountService.updateAccount(id, account);
        if (updatedAccount != null) {
        return ResponseEntity.ok(updatedAccount);
        } else {
        return ResponseEntity.notFound().build();
        }
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
        }

    @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<?> getAllAccountsByCustomerId(@PathVariable Long customerId) {
        String message = "Success";
        String errorResponse = "Error fetching customers accounts";
        Optional<Account> successData = this.accountService.getAccountsByCustomerId(customerId, message);

        if (successData.isPresent()) {
        CustomReply<Account> successResponse = new CustomReply<>(HttpStatus.OK.value(), message, successData.get());
        return ResponseEntity.ok(successResponse);
        } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }


        }

        }