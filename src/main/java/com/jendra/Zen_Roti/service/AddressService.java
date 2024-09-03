package com.jendra.Zen_Roti.service;

import com.jendra.Zen_Roti.entity.Address;
import com.jendra.Zen_Roti.exception.AddressNotFoundException;

import java.util.List;

public interface AddressService {

    Address save(Address address);
    Address findById(Long id) throws AddressNotFoundException;
    List<Address> findAll();
    void deleteById(Long id) throws AddressNotFoundException;

    Address update(Address address) throws AddressNotFoundException;



}
