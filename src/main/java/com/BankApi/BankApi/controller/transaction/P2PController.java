package com.BankApi.BankApi.controller.transaction;


import com.BankApi.BankApi.model.Transaction;
import com.BankApi.BankApi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions/p2p")
public class P2PController {

    private final TransactionService transactionService;

    @Autowired
    public P2PController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createP2PTransaction(@RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.p2p(transaction), HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getP2PsByAccountId(@PathVariable Long accountId) {
        return new ResponseEntity<>(transactionService.getAllP2PsByAccountId(accountId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllP2Ps() {
        return new ResponseEntity<>(transactionService.getAllP2Ps(), HttpStatus.OK);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getP2PById(@PathVariable Long transactionId) {
        return new ResponseEntity<>(transactionService.getP2PById(transactionId), HttpStatus.OK);
    }

}
