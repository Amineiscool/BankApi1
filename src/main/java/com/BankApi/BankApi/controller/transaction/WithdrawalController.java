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
public class WithdrawalController {

   /* private final TransactionService transactionService;

    @Autowired
    public WithdrawalController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/{accountId}/withdrawals")
    public ResponseEntity<Transaction> withdraw(@PathVariable Long accountId, @RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.createWithdrawal(accountId, transaction), HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}/withdrawals")
    public ResponseEntity<List<Transaction>> getAllWithdrawalsByAccountId(@PathVariable Long accountId) {
        return new ResponseEntity<>(transactionService.getAllWithdrawalsByAccountId(accountId), HttpStatus.OK);
    }

    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<Transaction> getWithdrawalById(@PathVariable Long withdrawalId) {
        return new ResponseEntity<>(transactionService.getWithdrawalById(withdrawalId), HttpStatus.OK);
    }

    @GetMapping("/withdrawals")
    public ResponseEntity<List<Transaction>> getAllWithdrawals() {
        return new ResponseEntity<>(transactionService.getAllWithdrawals(), HttpStatus.OK);
    }

    @PutMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<Transaction> updateWithdrawal(@PathVariable Long withdrawalId, @RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.updateWithdrawal(withdrawalId, transaction), HttpStatus.OK);
    }

    @DeleteMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<Void> deleteWithdrawal(@PathVariable Long withdrawalId) {
        transactionService.deleteWithdrawal(withdrawalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/
}