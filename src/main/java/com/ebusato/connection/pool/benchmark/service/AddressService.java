package com.ebusato.connection.pool.benchmark.service;

import com.ebusato.connection.pool.benchmark.model.Address;

import java.util.List;

public interface AddressService {

    Address save(Address a);

    List<Address> list();
}
