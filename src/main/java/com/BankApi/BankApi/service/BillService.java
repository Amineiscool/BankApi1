package com.BankApi.BankApi.service;

import com.BankApi.BankApi.model.Account;
import com.BankApi.BankApi.model.Bill;
import com.BankApi.BankApi.repo.AccountRepository;
import com.BankApi.BankApi.repo.BillRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final AccountRepository accountRepository;


    @Autowired
    public BillService(BillRepository billRepository, AccountRepository accountRepository) {
        this.billRepository = billRepository;
        this.accountRepository = accountRepository;

    }

    //createABill
    public Bill addBill(Long accountId, Bill bill) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            bill.setAccount(account);
            return billRepository.save(bill);
        }
        return null; // Handle the case when the account is not found
    }

    //get all bills for a specific account using its id
    public List<Bill> getAllBillForASpecificAccount(Long id) {
        return billRepository.findAllBillsByAccountId(id);
    }

    //edit-modify bill
    public Bill updateBill(Long accountId, Long billId, Bill bill) {
        Account account = accountRepository.findById(accountId).orElse(null);
        Bill bill1 = billRepository.findById(billId).orElse(null);
        if (bill1 != null) {
            bill1.setStatus(bill.getStatus());
            bill1.setPayee(bill.getPayee());
            bill1.setNickname(bill.getNickname());
            bill1.setCreationDate(bill.getCreationDate());
            bill1.setPaymentDate(bill.getPaymentDate());
            bill1.setRecurringDate(bill.getRecurringDate());
            bill1.setPaymentDate(bill.getPaymentDate());
            bill1.setUpcomingPaymentDate(bill.getUpcomingPaymentDate());
            bill1.setPaymentAmount(bill.getPaymentAmount());
        }
        //   bill.setAccount(Optional.ofNullable(account));
        return billRepository.save(bill);
    }

    public void deleteBill(Long billId) {
        billRepository.deleteById(billId);
    }

    public Bill getBillById(Long accountId) {


        return (Bill) billRepository.findByAccountId(accountId);

        // ...
    }
}