package com.BankApi.BankApi.service;

import com.BankApi.BankApi.errorException.exception.ResourceNotFoundException;
import com.BankApi.BankApi.model.Account;
import com.BankApi.BankApi.model.Customer;
import com.BankApi.BankApi.repo.AccountRepository;
import com.BankApi.BankApi.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, CustomerService customerService) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
    }


    protected void verifyAccount(Long accountId, String message) throws ResourceNotFoundException {
        if(!(this.accountRepository.existsById(accountId))) {
            throw (new ResourceNotFoundException(message));
        }
    }

    protected  void verifyCustomer (Long customerId, String message) throws ResourceNotFoundException {
        if(!(this.customerRepository.existsById(customerId))) {
            throw (new ResourceNotFoundException(message));
        }
    }

   /* public List<Account> getAllAccounts() {
        try {
            return (List<Account>) accountRepository.findAll();
        } catch (Exception exception) {
            throw new RuntimeException("Error fetching all accounts");
        }
    }*/

    public Iterable<Account> getAllAccounts(){
        return this.accountRepository.findAll();
    }


    /*public Account getAccountById(Long accountId) {
        return this.accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId))
        );
    }*/

    public Account getAccountById(Long accountId, String exceptionMessage){
        this.verifyAccount(accountId,exceptionMessage);
        return this.accountRepository.findById(accountId).get();
    }

    public Account createAccount(Account accountInfo, Long customerId, int code, String message) {
        this.verifyCustomer(customerId, message);
        Account account = new Account();
        // account.setType(AccountType.);
        account.setBalance(0.0);
        account.setNickname(accountInfo.getNickname());
        account.setRewards(0);
        account.setCustomer(this.customerRepository.findById(customerId).get());
        return this.accountRepository.save(account);

        /*Customer customer = accountRequest.customer;
        if(customer.getId() == null) customerService.createCustomer(customer);
        return accountRepository.save(accountRequest);*/
    }

   /* public Account updateAccount(Long id, Account accountRequest) {
        Account account = accountRepository.findById(id).orElse(new Account());
        account.nickname = accountRequest.nickname;
        account.balance = accountRequest.balance;
        account.rewards = accountRequest.rewards;
        account.customer = accountRequest.customer;
        try {
            return accountRepository.save(account);
        } catch (Exception exception) {
            throw new RuntimeException("Error updating account");
        }
    }*/


    public Account updateAccount (Long accountId, Account accountInfo){
        Account accountUpdate = this.accountRepository.findById(accountId).get();
        return this.accountRepository.save(accountUpdate);
    }

   /* public void deleteAccount(Long id) {
        accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", id))
        );
        try {
            accountRepository.deleteById(id);
        } catch (Exception exception) {
            throw new RuntimeException("Error deleting account");
        }
    }*/

    public void deleteAccount (Long accountId){
        this.accountRepository.deleteById(accountId);
    }

    public Optional<Account> getAccountsByCustomerId(long customerId, String message){
        this.verifyCustomer(customerId,message);
        return this.accountRepository.findById(customerId);
    }

}
