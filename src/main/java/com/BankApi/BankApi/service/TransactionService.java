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
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final DepositRepository depositRepository;
    private final WithdrawalRepository withdrawalRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository,
                              DepositRepository depositRepository, WithdrawalRepository withdrawalRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.depositRepository = depositRepository;
        this.withdrawalRepository = withdrawalRepository;
    }

    // Create a new transaction
    public Transaction createTransaction(Transaction transaction) {
        Account account = transaction.getAccount();
        if (account != null) {
            accountRepository.save(account);
        }
        return transactionRepository.save(transaction);
    }

    // Create a new deposit for a specific account
    public Deposit createDeposit(Long accountId, Deposit deposit) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        deposit.setType(TransactionType.DEPOSIT);
        deposit.setTransactionDate(LocalDateTime.now());

        account.deposit(BigDecimal.valueOf(deposit.getAmount()));

        deposit.setAccount(account);

        return depositRepository.save(deposit);
    }

    // Create a new withdrawal for a specific account
    public Withdrawal createWithdrawal(Long accountId, Withdrawal withdrawal) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        withdrawal.setType(TransactionType.WITHDRAWAL);
        withdrawal.setTransactionDate(LocalDateTime.now());

        if (!(account.getBalance().compareTo(BigDecimal.valueOf(withdrawal.getAmount())) >= 0)) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }

        account.withdraw(BigDecimal.valueOf(withdrawal.getAmount()));

        withdrawal.setAccount(account);

        return withdrawalRepository.save(withdrawal);
    }

    // Retrieve all transactions of a specific type associated with an account
    public List<Transaction> getAllTransactionsByAccountIdAndType(Long accountId, TransactionType type) {
        return transactionRepository.findByAccountIdAndType(accountId, type);
    }

    // Retrieve a transaction by its ID
    public Optional<Transaction> getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId);
    }

    // Update an existing transaction
    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Delete a transaction
    public void deleteTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    // Retrieve a deposit by its ID
    public Deposit getDepositById(Long depositId) {
        return depositRepository.findById(depositId)
                .orElseThrow(() -> new TransactionNotFoundException("Deposit not found"));
    }

    // Retrieve all deposits associated with a specific account
    public List<Deposit> getAllDepositsByAccountId(Long accountId) {
        return depositRepository.findByAccountIdAndType(accountId, TransactionType.DEPOSIT);
    }

    // Retrieve all deposits
    public List<Deposit> getAllDeposits() {
        return depositRepository.findByType(TransactionType.DEPOSIT);
    }

    // Update an existing deposit
    public Deposit updateDeposit(Long depositId, Deposit deposit) {
        Deposit existingDeposit = depositRepository.findById(depositId)
                .orElseThrow(() -> new TransactionNotFoundException("Deposit not found"));

        existingDeposit.setType(deposit.getType());
        existingDeposit.setStatus(deposit.getStatus());
        existingDeposit.setMedium(deposit.getMedium());
        existingDeposit.setAmount(deposit.getAmount());
        existingDeposit.setDescription(deposit.getDescription());

        return depositRepository.save(existingDeposit);
    }

    // Delete a deposit
    public void deleteDeposit(Long depositId) {
        depositRepository.deleteById(depositId);
    }

    // Retrieve a withdrawal by its ID
    public Withdrawal getWithdrawalById(Long withdrawalId) {
        return withdrawalRepository.findById(withdrawalId)
                .orElseThrow(() -> new TransactionNotFoundException("Withdrawal not found"));
    }

    // Retrieve all withdrawals associated with a specific account
    public List<Withdrawal> getAllWithdrawalsByAccountId(Long accountId) {
        return withdrawalRepository.findByAccountIdAndType(accountId, TransactionType.WITHDRAWAL);
    }

    // Retrieve all withdrawals
    public List<Withdrawal> getAllWithdrawals() {
        return withdrawalRepository.findByType(TransactionType.WITHDRAWAL);
    }

    // Update an existing withdrawal
    public Withdrawal updateWithdrawal(Long withdrawalId, Withdrawal withdrawal) {
        Withdrawal existingWithdrawal = withdrawalRepository.findById(withdrawalId)
                .orElseThrow(() -> new TransactionNotFoundException("Withdrawal not found"));

        existingWithdrawal.setType(withdrawal.getType());
        existingWithdrawal.setStatus(withdrawal.getStatus());
        existingWithdrawal.setMedium(withdrawal.getMedium());
        existingWithdrawal.setAmount(withdrawal.getAmount());
        existingWithdrawal.setDescription(withdrawal.getDescription());

        return withdrawalRepository.save(existingWithdrawal);
    }

    // Delete a withdrawal
    public void deleteWithdrawal(Long withdrawalId) {
        withdrawalRepository.deleteById(withdrawalId);
    }
}