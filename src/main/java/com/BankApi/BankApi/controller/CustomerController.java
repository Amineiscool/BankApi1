package com.BankApi.BankApi.controller;

import com.BankApi.BankApi.errorException.CustomerNotFoundException;
import com.BankApi.BankApi.model.Customer;
import com.BankApi.BankApi.reply.CustomReply;
import com.BankApi.BankApi.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            throw new CustomerNotFoundException("Customer not found with ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<CustomReply> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        CustomReply message = new CustomReply(200, "Customer account created");
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomReply> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) throws CustomerNotFoundException {
        CustomReply message = new CustomReply(200, "Customer account updated");
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        if (updatedCustomer != null) {
            return ResponseEntity.ok(message);
        } else {
            throw new CustomerNotFoundException("Customer not found with ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) throws CustomerNotFoundException {

        CustomReply message= new CustomReply(202,"deleted successfully");
        boolean deleted = customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}