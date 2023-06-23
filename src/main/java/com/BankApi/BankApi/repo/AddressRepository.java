package com.BankApi.BankApi.repo;

import com.BankApi.BankApi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
