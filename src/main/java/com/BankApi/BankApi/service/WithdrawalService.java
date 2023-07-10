package com.BankApi.BankApi.service;

import com.BankApi.BankApi.enums.TransactionType;
import com.BankApi.BankApi.errorException.exception.AccountNotFoundException;
import com.BankApi.BankApi.errorException.exception.InsufficientFundsException;
import com.BankApi.BankApi.errorException.exception.TransactionNotFoundException;
import com.BankApi.BankApi.model.Account;
import com.BankApi.BankApi.model.Deposit;
import com.BankApi.BankApi.model.Transaction;
import com.BankApi.BankApi.model.Withdrawal;
import com.BankApi.BankApi.repo.AccountRepository;
import com.BankApi.BankApi.repo.DepositRepository;
import com.BankApi.BankApi.repo.TransactionRepository;
import com.BankApi.BankApi.repo.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class WithdrawalService {
    private final TransactionService transactionService;
    private final AccountRepository accountRepository;
    private final WithdrawalRepository withdrawalRepository;

    @Autowired
    public WithdrawalService(TransactionService transactionService, AccountRepository accountRepository,
                             WithdrawalRepository withdrawalRepository) {
        this.transactionService = transactionService;
        this.accountRepository = accountRepository;
        this.withdrawalRepository = withdrawalRepository;
    }

    // Create a new withdrawal for a specific account
    public Withdrawal createWithdrawal(Long accountId, Withdrawal withdrawal) {
        return transactionService.createWithdrawal(accountId, withdrawal);
    }

    // Retrieve all withdrawals associated with a specific account
    public List<Withdrawal> getAllWithdrawalsByAccountId(Long accountId) {
        return transactionService.getAllWithdrawalsByAccountId(accountId);
    }

    // Retrieve a withdrawal by its ID
    public Withdrawal getWithdrawalById(Long withdrawalId) {
        return transactionService.getWithdrawalById(withdrawalId);
    }

    // Retrieve all withdrawals
    public List<Withdrawal> getAllWithdrawals() {
        return transactionService.getAllWithdrawals();
    }

    // Update an existing withdrawal
    public Withdrawal updateWithdrawal(Long withdrawalId, Withdrawal updatedWithdrawal) {
        return transactionService.updateWithdrawal(withdrawalId, updatedWithdrawal);
    }

    // Delete a withdrawal
    public void deleteWithdrawal(Long withdrawalId) {
        transactionService.deleteWithdrawal(withdrawalId);
    }
}