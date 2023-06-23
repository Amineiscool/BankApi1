package com.BankApi.BankApi.service;

import com.BankApi.BankApi.model.Address;
import com.BankApi.BankApi.model.Customer;
import com.BankApi.BankApi.repo.AddressRepository;
import com.BankApi.BankApi.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        for(Address address : customer.getAddresses()) {
            if (address.getId() == null) addressRepository.save(address);
        }
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id).orElse(new Customer());
        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setAddresses(updatedCustomer.getAddresses());
        return customerRepository.save(existingCustomer);
    }

    public boolean deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return false;
    }

}

