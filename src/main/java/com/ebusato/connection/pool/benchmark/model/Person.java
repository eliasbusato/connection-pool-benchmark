package com.ebusato.connection.pool.benchmark.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person_id")
    @SequenceGenerator(name = "seq_person_id", sequenceName = "seq_person_id", allocationSize = 1)
    private Long id;

    @NotEmpty(message = "Person name is required")
    @Size(max = 50, message = "Person name has a  maximum length of {max} characters")
    private String name;

    public Person() {
        super();
    }

    public Person(String name) {
        this.name = name;
    }
}
