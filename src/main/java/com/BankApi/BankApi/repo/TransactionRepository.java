package com.BankApi.BankApi.repo;

import com.BankApi.BankApi.enums.TransactionType;
import com.BankApi.BankApi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);
    Optional<Transaction> findByIdAndType(Long id, TransactionType type);
    List<Transaction> findByType(TransactionType type);
    List<Transaction> findByAccountIdAndType(Long accountId, TransactionType type);
}


