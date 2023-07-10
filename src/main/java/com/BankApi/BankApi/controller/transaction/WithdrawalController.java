package com.BankApi.BankApi.controller.transaction;

import com.BankApi.BankApi.errorException.exception.AccountNotFoundException;
import com.BankApi.BankApi.errorException.exception.InsufficientFundsException;
import com.BankApi.BankApi.errorException.exception.TransactionNotFoundException;
import com.BankApi.BankApi.model.Transaction;
import com.BankApi.BankApi.model.Withdrawal;
import com.BankApi.BankApi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class WithdrawalController {

    private final TransactionService transactionService;

    @Autowired
    public WithdrawalController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Create a new withdrawal for a specific account
    @PostMapping("/{accountId}/withdrawals")
    public ResponseEntity<?> withdraw(@PathVariable Long accountId, @RequestBody Withdrawal withdrawal) {
        try {
            Withdrawal createdWithdrawal = transactionService.createWithdrawal(accountId, withdrawal);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWithdrawal);
        } catch (AccountNotFoundException | InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Retrieve all withdrawals associated with a specific account
    @GetMapping("/{accountId}/withdrawals")
    public ResponseEntity<List<Withdrawal>> getAllWithdrawalsByAccountId(@PathVariable Long accountId) {
        List<Withdrawal> withdrawals = transactionService.getAllWithdrawalsByAccountId(accountId);
        return ResponseEntity.ok(withdrawals);
    }

    // Retrieve a withdrawal by its ID
    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId) {
        try {
            Withdrawal withdrawal = transactionService.getWithdrawalById(withdrawalId);
            return ResponseEntity.ok(withdrawal);
        } catch (TransactionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Retrieve all withdrawals across all accounts
    @GetMapping("/withdrawals")
    public ResponseEntity<List<Withdrawal>> getAllWithdrawals() {
        List<Withdrawal> withdrawals = transactionService.getAllWithdrawals();
        return ResponseEntity.ok(withdrawals);
    }

    // Update an existing withdrawal
    @PutMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@PathVariable Long withdrawalId, @RequestBody Withdrawal withdrawal) {
        try {
            Withdrawal updatedWithdrawal = transactionService.updateWithdrawal(withdrawalId, withdrawal);
            return ResponseEntity.ok(updatedWithdrawal);
        } catch (TransactionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Delete a withdrawal
    @DeleteMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<Void> deleteWithdrawal(@PathVariable Long withdrawalId) {
        try {
            transactionService.deleteWithdrawal(withdrawalId);
            return ResponseEntity.noContent().build();
        } catch (TransactionNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}