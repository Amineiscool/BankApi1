package com.BankApi.BankApi.model;

import com.BankApi.BankApi.enums.Medium;
import com.BankApi.BankApi.enums.Status;
import com.BankApi.BankApi.enums.TransactionType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
//
//@Entity
//@Table(name = "transactions")
//public class Transaction {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "transaction_type")
//    private TransactionType type;
//
//    @Column(name = "transaction_date")
//    private String transactionDate;
//
//    private Status status;
//
//    private Medium medium;
//
//    private double amount;
//
//    private String description;
//
//    @ManyToOne
//    @JoinColumn(name = "payee_id", referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Account payeeAccount;
//
//    @ManyToOne
//    @JoinColumn(name = "payer_id", referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Account payerAccount;
//
//    // Constructors, getters, and setters
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public TransactionType getType() {
//        return type;
//    }
//
//    public void setType(TransactionType type) {
//        this.type = type;
//    }
//
//    public String getTransactionDate() {
//        return transactionDate;
//    }
//
//    public void setTransactionDate(String transactionDate) {
//        this.transactionDate = transactionDate;
//    }
//
//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
//
//    public Medium getMedium() {
//        return medium;
//    }
//
//    public void setMedium(Medium medium) {
//        this.medium = medium;
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Account getPayeeAccount() {
//        return payeeAccount;
//    }
//
//    public void setPayeeAccount(Account account) {
//        this.payeeAccount = account;
//    }
//
//    public Account getPayerAccount() {
//        return payerAccount;
//    }
//
//    public void setPayerAccount(Account account) {
//        this.payerAccount = account;
//    }
//}