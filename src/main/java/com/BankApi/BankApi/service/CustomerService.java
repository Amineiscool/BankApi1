package com.BankApi.BankApi.service;

import com.BankApi.BankApi.errorException.CustomerNotFoundException;
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
        return createdCustomer;
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) throws CustomerNotFoundException {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));

        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());

        Set<Address> updatedAddresses = new HashSet<>();

        for (Address address : updatedCustomer.getAddresses()) {
            if (address.getId() != null) {
                // Existing address, update its properties
                Address existingAddress = addressRepository.findById(address.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Address not found with ID: " + address.getId()));

                existingAddress.setStreetNumber(address.getStreetNumber());
                existingAddress.setStreetName(address.getStreetName());
                existingAddress.setCity(address.getCity());
                existingAddress.setState(address.getState());
                existingAddress.setZip(address.getZip());

                updatedAddresses.add(existingAddress);
            } else {
                // New address, save it and set the customer
                address.setCustomer(existingCustomer);
                Address savedAddress = addressRepository.save(address);
                updatedAddresses.add(savedAddress);
            }
        }

        // Remove any addresses that were not included in the update
        existingCustomer.getAddresses().removeIf(address -> !updatedAddresses.contains(address));

        // Update the addresses of the existing customer
        existingCustomer.getAddresses().clear();
        existingCustomer.getAddresses().addAll(updatedAddresses);

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




