package com.ebusato.connection.pool.benchmark.service;

import com.ebusato.connection.pool.benchmark.model.Person;

import java.util.List;

public interface PersonService {

    Person save(Person s);

    List<Person> list();
}
