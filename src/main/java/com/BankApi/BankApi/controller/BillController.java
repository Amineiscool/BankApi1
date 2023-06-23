package com.BankApi.BankApi.controller;

import com.BankApi.BankApi.model.Account;
import com.BankApi.BankApi.model.Bill;
import com.BankApi.BankApi.reply.CustomReply;
import com.BankApi.BankApi.repo.AccountRepository;
import com.BankApi.BankApi.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts/{accountId}/bills")
public class BillController {

    private final BillService billService;
    private final AccountRepository accountRepository;

    public BillController(BillService billService, AccountRepository accountRepository) {
        this.billService = billService;
        this.accountRepository = accountRepository;
    }

    @PostMapping
    public ResponseEntity<CustomReply> createBill(@PathVariable Long accountId, @RequestBody Bill bill) {
        billService.addBill(accountId, bill);
        CustomReply message=new CustomReply(" 201","Created bill and added it to the account" );
        return  ResponseEntity.status( HttpStatus.CREATED).body(message);
    }


    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills(@PathVariable Long accountId) {
        return new ResponseEntity<>(billService.getAllBillForASpecificAccount(accountId), HttpStatus.OK);
    }

    @PutMapping("/{billId}")
    public ResponseEntity<CustomReply> updateBill(@PathVariable Long accountId, @PathVariable Long billId, @RequestBody Bill bill) {
        billService.updateBill(accountId, billId, bill);
        CustomReply message= new CustomReply("202","Accepted customer modification");
        return  ResponseEntity.status( HttpStatus.OK).body(message);
    }

    @GetMapping("/{billId}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long billId) {
        Bill bill = billService.getBillById(billId);
        if (bill != null) {
            return ResponseEntity.ok(bill);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{billId}")
    public ResponseEntity<Void> removeBill(@PathVariable Long billId) {
        billService.deleteBill(billId);
        return ResponseEntity.noContent().build();
    }
}
