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
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId))
        );
        account.setBalance(account.getBalance() + transaction.getAmount());
        accountRepository.save(account);
        transaction.setType(TransactionType.DEPOSIT);
        return transactionRepository.save(transaction);
    }

    public Transaction withdraw(Long accountId, Transaction transaction) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId))
        );
        account.setBalance(account.getBalance() - transaction.getAmount());
        accountRepository.save(account);
        transaction.setType(TransactionType.WITHDRAWAL);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllDepositsByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId))
        );
        return transactionRepository.findAllDepositsByAccountId(accountId);
    }

    public List<Transaction> getAllWithdrawalsByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId))
        );
        return transactionRepository.findAllWithdrawalsByAccountId(accountId);
    }

    public List<Transaction> getAllTransactionsByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId))
        );
        return transactionRepository.findAllTransactionsByAccountId(accountId);
    }

    public List<Transaction> getAllDeposits() {
        return transactionRepository.findAllDeposits();
    }

    public List<Transaction> getAllWithdrawals() {
        return transactionRepository.findAllWithdrawals();
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getDepositById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Deposit with ID %s not found", id))
        );
        if (!transaction.getType().equals(TransactionType.DEPOSIT)) {
            throw new RuntimeException(String.format("Transaction with ID %s is not a Deposit", id));
        }
        return transaction;
    }

    public Transaction getWithdrawalById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Withdrawal with ID %s not found", id))
        );
        if (!transaction.getType().equals(TransactionType.WITHDRAWAL)) {
            throw new RuntimeException(String.format("Transaction with ID %s is not a Withdrawal", id));
        }
        return transaction;
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Transaction with ID %s not found", id))
        );
    }

    public void deleteDeposit(Long depositId) {
        transactionRepository.deleteById(depositId);
    }

    public void deleteWithdrawal(Long withdrawalId) {
        transactionRepository.deleteById(withdrawalId);
    }

    public Transaction updateDeposit(Long depositId, Transaction updatedTransaction) {
        Transaction transaction = transactionRepository.findById(depositId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Deposit with ID %s not found", depositId))
        );

        transaction.setAmount(updatedTransaction.getAmount());
        transaction.setDate(updatedTransaction.getDate());
        // Update any other properties if necessary
        // ...

        return transactionRepository.save(transaction);
    }

    public Transaction updateWithdrawal(Long withdrawalId, Transaction updatedTransaction) {
        Transaction transaction = transactionRepository.findById(withdrawalId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Withdrawal with ID %s not found", withdrawalId))
        );

        transaction.setAmount(updatedTransaction.getAmount());
        transaction.setDate(updatedTransaction.getDate());
        // Update any other properties if necessary
        // ...

        return transactionRepository.save(transaction);
    }
}