package com.ebusato.connection.pool.benchmark.repository;

import com.ebusato.connection.pool.benchmark.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAllByName(String name);
}
