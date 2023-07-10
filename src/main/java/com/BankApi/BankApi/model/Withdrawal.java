package com.BankApi.BankApi.model;

import com.BankApi.BankApi.enums.Medium;
import com.BankApi.BankApi.enums.Status;
import com.BankApi.BankApi.enums.TransactionType;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "withdrawals")
public class Withdrawal extends Transaction {

    public Withdrawal() {
        // Default constructor
    }

    public Withdrawal(TransactionType type, LocalDateTime transactionDate, Status status, Medium medium, double amount, String description) {
        super(type, transactionDate, status, medium, amount, description);
    }

    // Add any additional methods specific to Withdrawal
}

