package com.BankApi.BankApi.repo;

import com.BankApi.BankApi.enums.TransactionType;
import com.BankApi.BankApi.model.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
    List<Withdrawal> findByAccountIdAndType(Long accountId, TransactionType type);
    List<Withdrawal> findByType(TransactionType type);
}
