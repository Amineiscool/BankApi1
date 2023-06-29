package com.BankApi.BankApi.service;

import com.BankApi.BankApi.model.Address;
import com.BankApi.BankApi.model.Customer;
import com.BankApi.BankApi.repo.AddressRepository;
import com.BankApi.BankApi.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Set<Address> addresses = new HashSet<>();
        for (Address address : customer.getAddresses()) {
            addresses.add(addressRepository.save(address));
        }
        customer.setAddresses(addresses);
        Customer createdCustomer = customerRepository.save(customer);

        // Fetch the addresses again to populate the fields with database values
        Set<Address> createdAddresses = new HashSet<>();
        for (Address address : createdCustomer.getAddresses()) {
            createdAddresses.add(addressRepository.findById(address.getId()).orElse(null));
        }
        createdCustomer.setAddresses(createdAddresses);

        return createdCustomer;
    }


    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id).orElse(new Customer());
        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setAddresses(updatedCustomer.getAddresses());
        return customerRepository.save(existingCustomer);
    }

    public boolean deleteCustomer(Long id) {
        try {
            Customer customer = customerRepository.findById(id).orElse(null);
            if (customer != null) {
                List<Address> addresses = new ArrayList<>(customer.getAddresses());
                customer.getAddresses().clear();
                customerRepository.save(customer);
                for (Address address : addresses) {
                    addressRepository.delete(address);
                }
                customerRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}





