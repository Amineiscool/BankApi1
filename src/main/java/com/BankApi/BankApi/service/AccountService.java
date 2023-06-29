package com.BankApi.BankApi.service;

import com.BankApi.BankApi.errorException.exception.ResourceNotFoundException;
import com.BankApi.BankApi.model.Account;
import com.BankApi.BankApi.model.Customer;
import com.BankApi.BankApi.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;

    @Autowired
    public AccountService(AccountRepository accountRepository, CustomerService customerService) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
    }

    // Retrieve all accounts
    public List<Account> getAllAccounts() {
        try {
            return accountRepository.findAll();
        } catch (Exception exception) {
            throw new RuntimeException("Error fetching all accounts");
        }
    }

    // Retrieve an account by its ID
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", id))
        );
    }

//    // Add a new account
//    public Account addAccount(Account accountRequest) {
//        Customer customer = accountRequest.customer;
//        // Create a new customer if the customer ID is null
//        if (customer.getId() == null) customerService.createCustomer(customer);
//        try {
//            return accountRepository.save(accountRequest);
//        } catch (Exception exception) {
//            throw new RuntimeException("Error adding account");
//        }
//    }

    // Update an existing account
//    public Account updateAccount(Long id, Account accountRequest) {
//        Account account = accountRepository.findById(id).orElse(new Account());
//        // Update account details
//        account.nickname = accountRequest.nickname;
//        account.balance = accountRequest.balance;
//        account.rewards = accountRequest.rewards;
//        account.customer = accountRequest.customer;
//        try {
//            return accountRepository.save(account);
//        } catch (Exception exception) {
//            throw new RuntimeException("Error updating account");
//        }
//    }

    // Delete an account
    public void deleteAccount(Long id) {
        // Retrieve the account by its ID, or throw an exception if not found
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", id))
        );
        try {
            // Delete the account
            accountRepository.delete(account);
        } catch (Exception exception) {
            throw new RuntimeException("Error deleting account");
        }
    }

}
