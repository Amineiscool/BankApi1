package com.BankApi.BankApi.controller.transaction;


import com.BankApi.BankApi.model.Deposit;
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

    // Create a new deposit transaction for a specific account
    @PostMapping("/{accountId}/deposits")
    public ResponseEntity<Transaction> createDeposit(@PathVariable Long accountId, @RequestBody Deposit deposit) {
        return new ResponseEntity<>(transactionService.createDeposit(accountId, deposit), HttpStatus.CREATED);
    }

    // Retrieve all deposits associated with a specific account
    @GetMapping("/{accountId}/deposits")
    public ResponseEntity<List<Deposit>> getAllDepositsByAccountId(@PathVariable Long accountId) {
        return new ResponseEntity<>(transactionService.getAllDepositsByAccountId(accountId), HttpStatus.OK);
    }

    // Retrieve a deposit transaction by its ID
    @GetMapping("/deposits/{depositId}")
    public ResponseEntity<Deposit> getDepositById(@PathVariable Long depositId) {
        return new ResponseEntity<>(transactionService.getDepositById(depositId), HttpStatus.OK);
    }

    // Retrieve all deposit transactions across all accounts
    @GetMapping("/deposits")
    public ResponseEntity<List<Deposit>> getAllDeposits() {
        return new ResponseEntity<>(transactionService.getAllDeposits(), HttpStatus.OK);
    }

    // Update an existing deposit transaction
    @PutMapping("/deposits/{depositId}")
    public ResponseEntity<Deposit> updateDeposit(@PathVariable Long depositId, @RequestBody Deposit deposit) {
        return new ResponseEntity<>(transactionService.updateDeposit(depositId, deposit), HttpStatus.OK);
    }

    // Delete a deposit transaction
    @DeleteMapping("/deposits/{depositId}")
    public ResponseEntity<Void> deleteDeposit(@PathVariable Long depositId) {
        transactionService.deleteDeposit(depositId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
