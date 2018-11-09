package com.ebusato.connection.pool.benchmark.service;

import com.ebusato.connection.pool.benchmark.model.Person;
import com.ebusato.connection.pool.benchmark.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public Person save(Person p) {
        return this.personRepository.save(p);
    }

    @Override
    public List<Person> list() {
        return this.personRepository.findAll();
    }

    @Override
    public List<Person> findByName(String name) {
        return this.personRepository.findAllByName(name);
    }
}
