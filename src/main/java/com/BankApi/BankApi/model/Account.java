package com.BankApi.BankApi.model;

import com.BankApi.BankApi.enums.AccountType;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Long id;

    @Column
    public String nickname;

    @Column
    public Integer rewards;

    @Column
    public Double balance;

    @Column
    public AccountType type;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    public Customer customer;

    public Account() {

    }

    public Account(Long id, String nickname, Integer rewards, Double balance, AccountType type, Customer customer) {
        this.id = id;
        this.nickname = nickname;
        this.rewards = rewards;
        this.balance = balance;
        this.type = type;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getRewards() {
        return rewards;
    }

    public void setRewards(Integer rewards) {
        this.rewards = rewards;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
