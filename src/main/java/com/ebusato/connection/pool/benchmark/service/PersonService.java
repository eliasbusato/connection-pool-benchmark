package com.ebusato.connection.pool.benchmark.service;

import com.ebusato.connection.pool.benchmark.model.Person;

import java.util.List;

public interface PersonService {

    Person save(Person p);

    void truncate();

    List<Person> list();

    List<Person> findByName(String name);
}
