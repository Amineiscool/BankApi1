package com.BankApi.BankApi.controller;

import com.BankApi.BankApi.model.Account;
import com.BankApi.BankApi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/accounts")
//public class AccountController {
//
//    private final AccountService accountService;
//
//    @Autowired
//    public AccountController(AccountService accountService) {
//        this.accountService = accountService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Account>> getAllAccounts() {
//        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
//        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
//        return new ResponseEntity<>(accountService.addAccount(account), HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
//        return new ResponseEntity<>(accountService.updateAccount(id,account), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
//        accountService.deleteAccount(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

//}

