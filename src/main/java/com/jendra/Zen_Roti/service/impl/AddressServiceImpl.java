package com.jendra.Zen_Roti.service;

import com.jendra.Zen_Roti.entity.Address;
import com.jendra.Zen_Roti.exception.AddressNotFoundException;
import com.jendra.Zen_Roti.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address) {
        if(address==null){
            throw new NullPointerException("Null address not allowed, provide none-null object");
        }
        return addressRepository.save(address);
    }

    @Override
    public Address findById(Long id) throws AddressNotFoundException {
        return addressRepository.findById(id).orElseThrow(()-> new AddressNotFoundException("Address "+id+" not found! Use existing id or create one"));
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws AddressNotFoundException {
        if(!addressRepository.existsById(id)){
            throw new AddressNotFoundException("Can't delete, address "+id+" not found! Use existing id");
        }
        addressRepository.deleteById(id);
    }

    @Override
    public Address update(Address address) throws AddressNotFoundException {
        if(addressRepository.existsById(address.getId())){
            return addressRepository.save(address);
        }
        throw new AddressNotFoundException("Can't update, address "+address.getId()+" not found! Use existing id");
    }
}
