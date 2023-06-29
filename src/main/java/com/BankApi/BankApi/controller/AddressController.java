package com.BankApi.BankApi.controller;

import com.BankApi.BankApi.model.Address;
import com.BankApi.BankApi.reply.CustomReply;
import com.BankApi.BankApi.service.AddressService;
//import jdk.internal.access.JavaIOFileDescriptorAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/addresses")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        logger.info("Creating new address", address);
        return new ResponseEntity<>(addressService.addAddress(address), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Address>> getAllAddresses() {
        logger.debug("getting  addresses");
        Iterable<Address> addresses = addressService.getAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Address>> getAddressById(@PathVariable Long id) {
        logger.debug("getting address by id", id);
        Optional<Address> address = addressService.getAddressById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        logger.debug("Updating address : , New address:", id, address);
        CustomReply message= new CustomReply(202,"Accepted address modification");
        return new ResponseEntity<>(addressService.updateAddress(id, address), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        logger.warn("Deleting address:", id);
        addressService.deleteAddress(id);

        CustomReply message=new CustomReply(201,"delete address successful");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
