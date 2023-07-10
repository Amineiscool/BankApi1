package com.BankApi.BankApi.repo;

import com.BankApi.BankApi.enums.TransactionType;
import com.BankApi.BankApi.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findByAccountIdAndType(Long accountId, TransactionType type);
    List<Deposit> findByType(TransactionType type);
}
