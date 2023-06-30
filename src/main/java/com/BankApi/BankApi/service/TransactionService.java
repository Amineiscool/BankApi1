//package com.BankApi.BankApi.service;
//
//import com.BankApi.BankApi.enums.Medium;
//import com.BankApi.BankApi.enums.Status;
//import com.BankApi.BankApi.enums.TransactionType;
//import com.BankApi.BankApi.errorException.exception.AccountNotFoundException;
//import com.BankApi.BankApi.errorException.exception.InsufficientFundsException;
//import com.BankApi.BankApi.errorException.exception.ResourceNotFoundException;
//import com.BankApi.BankApi.errorException.exception.TransactionNotFoundException;
//import com.BankApi.BankApi.model.Account;
//import com.BankApi.BankApi.model.Transaction;
//import com.BankApi.BankApi.repo.AccountRepository;
//import com.BankApi.BankApi.repo.TransactionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//@Service
//public class TransactionService {
//
//   /* private final TransactionRepository transactionRepository;
//    private final AccountRepository accountRepository;
//
//    @Autowired
//    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
//        this.transactionRepository = transactionRepository;
//        this.accountRepository = accountRepository;
//    }
//
//    public Transaction createDeposit(Long accountId, Transaction transaction) {
//        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found"));
//        double depositAmount = transaction.getAmount();
//        double newBalance = account.getBalance() + depositAmount;
//        account.setBalance(newBalance);
//        transaction.setType(TransactionType.DEPOSIT);
//        transaction.setStatus(Status.COMPLETED);
//        transaction.setMedium(Medium.BALANCE);
//        transaction.setTransactionDate(getCurrentDateTime());
//        return transactionRepository.save(transaction);
//    }
//
//    public List<Transaction> getAllDepositsByAccountId(Long accountId) {
//        return transactionRepository.findByAccountIdAndType(accountId, TransactionType.DEPOSIT);
//    }
//
//    public Transaction getDepositById(Long depositId) {
//        return transactionRepository.findByIdAndType(depositId, TransactionType.DEPOSIT)
//                .orElseThrow(() -> new TransactionNotFoundException("Deposit not found"));
//    }
//
//    public List<Transaction> getAllDeposits() {
//        return transactionRepository.findByType(TransactionType.DEPOSIT);
//    }
//
//    public Transaction createWithdrawal(Long accountId, Transaction transaction) {
//        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found"));
//        double withdrawalAmount = transaction.getAmount();
//        if (withdrawalAmount > account.getBalance()) {
//            throw new InsufficientFundsException("Insufficient funds for withdrawal");
//        }
//        double newBalance = account.getBalance() - withdrawalAmount;
//        account.setBalance(newBalance);
//        transaction.setType(TransactionType.WITHDRAWAL);
//        transaction.setStatus(Status.COMPLETED);
//        transaction.setMedium(Medium.BALANCE);
//        transaction.setTransactionDate(getCurrentDateTime());
//        return transactionRepository.save(transaction);
//    }
//
//    public List<Transaction> getAllWithdrawalsByAccountId(Long accountId) {
//        return transactionRepository.findByAccountIdAndType(accountId, TransactionType.WITHDRAWAL);
//    }
//
//    public Transaction getWithdrawalById(Long withdrawalId) {
//        return transactionRepository.findByIdAndType(withdrawalId, TransactionType.WITHDRAWAL)
//                .orElseThrow(() -> new TransactionNotFoundException("Withdrawal not found"));
//    }
//
//    public List<Transaction> getAllWithdrawals() {
//        return transactionRepository.findByType(TransactionType.WITHDRAWAL);
//    }
//
//    public List<Transaction> getAllTransactionsByAccountId(Long accountId) {
//        return transactionRepository.findByAccountId(accountId);
//    }
//
//    public Transaction getTransactionById(Long transactionId) {
//        return transactionRepository.findById(transactionId)
//                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
//    }
//
//    public Transaction updateTransaction(Long transactionId, Transaction updatedTransaction) {
//        Transaction transaction = transactionRepository.findById(transactionId)
//                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
//
//        transaction.setType(updatedTransaction.getType());
//        transaction.setStatus(updatedTransaction.getStatus());
//        transaction.setMedium(updatedTransaction.getMedium());
//        transaction.setAmount(updatedTransaction.getAmount());
//        transaction.setDescription(updatedTransaction.getDescription());
//
//        return transactionRepository.save(transaction);
//    }
//
//    public void deleteTransaction(Long transactionId) {
//        Transaction transaction = transactionRepository.findById(transactionId)
//                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
//
//        transactionRepository.delete(transaction);
//    }
//
//    public Transaction updateDeposit(Long depositId, Transaction updatedTransaction) {
//        Transaction deposit = getDepositById(depositId);
//        deposit.setType(updatedTransaction.getType());
//        deposit.setStatus(updatedTransaction.getStatus());
//        deposit.setMedium(updatedTransaction.getMedium());
//        deposit.setAmount(updatedTransaction.getAmount());
//        deposit.setDescription(updatedTransaction.getDescription());
//        return transactionRepository.save(deposit);
//    }
//
//    public Transaction updateWithdrawal(Long withdrawalId, Transaction updatedTransaction) {
//        Transaction withdrawal = getWithdrawalById(withdrawalId);
//        withdrawal.setType(updatedTransaction.getType());
//        withdrawal.setStatus(updatedTransaction.getStatus());
//        withdrawal.setMedium(updatedTransaction.getMedium());
//        withdrawal.setAmount(updatedTransaction.getAmount());
//        withdrawal.setDescription(updatedTransaction.getDescription());
//        return transactionRepository.save(withdrawal);
//    }
//
//    public void deleteDeposit(Long depositId) {
//        Transaction deposit = getDepositById(depositId);
//        transactionRepository.delete(deposit);
//    }
//
//    public void deleteWithdrawal(Long withdrawalId) {
//        Transaction withdrawal = getWithdrawalById(withdrawalId);
//        transactionRepository.delete(withdrawal);
//    }
//
//    private String getCurrentDateTime() {
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        return now.format(formatter);
//    }*/
//}
//
//
