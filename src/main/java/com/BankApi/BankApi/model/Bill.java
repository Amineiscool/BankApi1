package com.BankApi.BankApi.model;

import com.BankApi.BankApi.enums.Status;
import javax.persistence.*;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Status status;

    @Column
    private String payee;

    @Column
    private String nickname;

    @Column(name = "creation_date")
    private String creationDate;

    @Column(name = "payment_date")
    private String paymentDate;

    @Column(name = "recurring_date")
    private Integer recurringDate;

    @Column(name = "upcoming_payment_date")
    private String upcomingPaymentDate;

    @Column(name = "payment_amount")
    private Double paymentAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    // Constructors

    public Bill() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getRecurringDate() {
        return recurringDate;
    }

    public void setRecurringDate(Integer recurringDate) {
        this.recurringDate = recurringDate;
    }

    public String getUpcomingPaymentDate() {
        return upcomingPaymentDate;
    }

    public void setUpcomingPaymentDate(String upcomingPaymentDate) {
        this.upcomingPaymentDate = upcomingPaymentDate;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}


