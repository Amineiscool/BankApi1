package com.BankApi.BankApi.service;

import com.BankApi.BankApi.enums.TransactionType;
import com.BankApi.BankApi.errorException.exception.ResourceNotFoundException;
import com.BankApi.BankApi.model.Account;
import com.BankApi.BankApi.model.Transaction;
import com.BankApi.BankApi.repo.AccountRepository;
import com.BankApi.BankApi.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public Transaction deposit(Long accountId, Transaction transaction) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId)));

        account.setBalance(account.getBalance() + transaction.getAmount());
        accountRepository.save(account);

        transaction.setType(TransactionType.DEPOSIT);
        transaction.setPayeeAccount(account);
        transaction.setPayerAccount(null);
        return transactionRepository.save(transaction);
    }

    public Transaction withdraw(Long accountId, Transaction transaction) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId)));

        double withdrawalAmount = transaction.getAmount();
        if (withdrawalAmount > account.getBalance()) {
            throw new InsufficientFundsException("Insufficient funds in the account for withdrawal");
        }

        account.setBalance(account.getBalance() - withdrawalAmount);
        accountRepository.save(account);

        transaction.setType(TransactionType.WITHDRAWAL);
        transaction.setPayeeAccount(null);
        transaction.setPayerAccount(account);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllDepositsByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId)));

        return transactionRepository.findAllDepositsByAccountId(accountId);
    }

    public List<Transaction> getAllWithdrawalsByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId)));

        return transactionRepository.findAllWithdrawalsByAccountId(accountId);
    }

    public List<Transaction> getAllTransactionsByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId)));

        return transactionRepository.findAllTransactionsByAccountId(accountId);
    }

    public List<Transaction> getAllDeposits() {
        return transactionRepository.findAllDeposits();
    }

    public List<Transaction> getAllWithdrawals() {
        return transactionRepository.findAllWithdrawals();
    }

    public Transaction getDepositById(Long id) {
        Transaction deposit = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Deposit with ID %s not found", id)));

        if (deposit.getType() != TransactionType.DEPOSIT) {
            throw new IllegalArgumentException(String.format("Transaction with ID %s is not a Deposit", id));
        }

        return deposit;
    }

    public Transaction getWithdrawalById(Long id) {
        Transaction withdrawal = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Withdrawal with ID %s not found", id)));

        if (withdrawal.getType() != TransactionType.WITHDRAWAL) {
            throw new IllegalArgumentException(String.format("Transaction with ID %s is not a Withdrawal", id));
        }

        return withdrawal;
    }
}