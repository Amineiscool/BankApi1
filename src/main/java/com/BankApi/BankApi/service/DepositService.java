package com.BankApi.BankApi.service;

import com.BankApi.BankApi.enums.TransactionType;
import com.BankApi.BankApi.errorException.exception.AccountNotFoundException;
import com.BankApi.BankApi.errorException.exception.TransactionNotFoundException;
import com.BankApi.BankApi.model.Account;
import com.BankApi.BankApi.model.Deposit;
import com.BankApi.BankApi.repo.AccountRepository;
import com.BankApi.BankApi.repo.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepositService {
    private final TransactionService transactionService;
    private final AccountRepository accountRepository;
    private final DepositRepository depositRepository;

    @Autowired
    public DepositService(TransactionService transactionService, AccountRepository accountRepository,
                          DepositRepository depositRepository) {
        this.transactionService = transactionService;
        this.accountRepository = accountRepository;
        this.depositRepository = depositRepository;
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

    // Retrieve all deposits associated with a specific account
    public List<Deposit> getAllDepositsByAccountId(Long accountId) {
        return depositRepository.findByAccountIdAndType(accountId, TransactionType.DEPOSIT);
    }

    // Retrieve a deposit by its ID
    public Deposit getDepositById(Long depositId) {
        return depositRepository.findById(depositId)
                .orElseThrow(() -> new TransactionNotFoundException("Deposit not found"));
    }

    // Retrieve all deposits
    public List<Deposit> getAllDeposits() {
        return depositRepository.findByType(TransactionType.DEPOSIT);
    }

    // Update an existing deposit
    public Deposit updateDeposit(Long depositId, Deposit updatedDeposit) {
        Deposit deposit = depositRepository.findById(depositId)
                .orElseThrow(() -> new TransactionNotFoundException("Deposit not found"));

        deposit.setType(updatedDeposit.getType());
        deposit.setStatus(updatedDeposit.getStatus());
        deposit.setMedium(updatedDeposit.getMedium());
        deposit.setAmount(updatedDeposit.getAmount());
        deposit.setDescription(updatedDeposit.getDescription());

        return depositRepository.save(deposit);
    }

    // Delete a deposit
    public void deleteDeposit(Long depositId) {
        depositRepository.deleteById(depositId);
    }
}