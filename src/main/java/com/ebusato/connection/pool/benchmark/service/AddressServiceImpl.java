package com.ebusato.connection.pool.benchmark.service;

import com.ebusato.connection.pool.benchmark.model.Address;
import com.ebusato.connection.pool.benchmark.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address a) {
        return addressRepository.save(a);
    }

    @Override
    public List<Address> list() {
        return addressRepository.findAll();
    }
}
