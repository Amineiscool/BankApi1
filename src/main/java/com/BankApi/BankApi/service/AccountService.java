package com.BankApi.BankApi.service;

import com.BankApi.BankApi.errorException.exception.ResourceNotFoundException;
import com.BankApi.BankApi.model.Account;
import com.BankApi.BankApi.model.Customer;
import com.BankApi.BankApi.repo.AccountRepository;
import com.BankApi.BankApi.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long accountId) throws ResourceNotFoundException {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }

    public Account createAccount(Account accountInfo, Long customerId) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Account account = new Account();
        account.setNickname(accountInfo.getNickname());
        account.setRewards(0);
        account.setBalance(accountInfo.getBalance());
        account.setType(accountInfo.getType());
        // account.setCustomer(customer);

        return accountRepository.save(account);
    }


    public Account updateAccount(Long accountId, Account accountInfo) throws ResourceNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        account.setNickname(accountInfo.getNickname());
        account.setRewards(accountInfo.getRewards());
        account.setBalance(accountInfo.getBalance());
        account.setType(accountInfo.getType());

        return accountRepository.save(account);
    }

    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}