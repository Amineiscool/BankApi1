package com.BankApi.BankApi.controller.transaction;


import com.BankApi.BankApi.model.Transaction;
import com.BankApi.BankApi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class DepositController {

    private final TransactionService transactionService;

    @Autowired
    public DepositController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/{accountId}/deposits")
    public ResponseEntity<Transaction> createDeposit(@PathVariable Long accountId, @RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.deposit(accountId, transaction), HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}/deposits")
    public ResponseEntity<List<Transaction>> getAllDepositsByAccountId(@PathVariable Long accountId) {
        return new ResponseEntity<>(transactionService.getAllDepositsByAccountId(accountId), HttpStatus.OK);
    }

    @GetMapping("/deposits/{depositId}")
    public ResponseEntity<Transaction> getDepositById(@PathVariable Long depositId) {
        return new ResponseEntity<>(transactionService.getDepositById(depositId), HttpStatus.OK);
    }

    @GetMapping("/deposits")
    public ResponseEntity<List<Transaction>> getAllDeposits() {
        return new ResponseEntity<>(transactionService.getAllDeposits(), HttpStatus.OK);
    }

    @PutMapping("/deposits/{depositId}")
    public ResponseEntity<Transaction> updateDeposit(@PathVariable Long depositId, @RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.updateDeposit(depositId, transaction), HttpStatus.OK);
    }

    @DeleteMapping("/deposits/{depositId}")
    public ResponseEntity<Void> deleteDeposit(@PathVariable Long depositId) {
        transactionService.deleteDeposit(depositId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
