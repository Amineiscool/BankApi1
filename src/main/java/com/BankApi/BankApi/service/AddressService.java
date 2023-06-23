package com.BankApi.BankApi.service;

import com.BankApi.BankApi.model.Address;
import com.BankApi.BankApi.repo.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address addAddress(Address address) {
        logger.info("Adding new address: {}", address);
        return addressRepository.save(address);
    }

    public Iterable<Address> getAllAddresses() {
        logger.debug("Retrieving all addresses");
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(Long id) {
        logger.debug("Retrieving address with ID: {}", id);
        return addressRepository.findById(id);
    }

    public Address updateAddress(Long id, Address newAddress) {
        logger.debug("Updating address with ID: {}, New address: {}", id, newAddress);
        Address address = addressRepository.findById(id).orElse(new Address());
        address.setStreetNumber(newAddress.getStreetNumber());
        address.setStreetName(newAddress.getStreetName());
        address.setCity(newAddress.getCity());
        address.setState(newAddress.getState());
        address.setZip(newAddress.getZip());
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        logger.warn("Deleting address with ID: {}", id);
        addressRepository.deleteById(id);
    }

}
