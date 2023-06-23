package com.BankApi.BankApi.repo;

import com.BankApi.BankApi.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findByAccountId(Long accountId);

    @Query(value = "select * from bills where account_id = ?1", nativeQuery = true)
    List<Bill> findAllBillsByAccountId(Long id);

}
