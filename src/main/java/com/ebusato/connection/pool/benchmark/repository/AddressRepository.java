package com.ebusato.connection.pool.benchmark.repository;

import com.ebusato.connection.pool.benchmark.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByStreet(String street);
}
