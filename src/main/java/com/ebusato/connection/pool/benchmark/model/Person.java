package com.ebusato.connection.pool.benchmark.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person_id")
    @SequenceGenerator(name = "seq_person_id", sequenceName = "seq_person_id", allocationSize = 1)
    private Long id;

    @NotEmpty(message = "Person name is required")
    @Size(max = 50, message = "Person name has a  maximum length of {max} characters")
    private String name;

    @NotNull(message = "Address is required")
    @Size(min = 1, message = "Need at least one address")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "person")
    private List<Address> addresses = new ArrayList<>();

    public Person() {
        super();
    }

    public Person(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
