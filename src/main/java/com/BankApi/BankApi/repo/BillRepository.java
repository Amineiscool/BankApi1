package com.BankApi.BankApi.repo;

import com.BankApi.BankApi.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {



    @Query(value = "SELECT * FROM bills WHERE account_id = :accountId", nativeQuery = true)
    List<Bill> findAllBillsByAccountId(@Param("accountId") Long accountId);

}