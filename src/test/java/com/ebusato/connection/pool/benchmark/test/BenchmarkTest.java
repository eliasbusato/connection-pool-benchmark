package com.ebusato.connection.pool.benchmark.test;

import com.ebusato.connection.pool.benchmark.model.Address;
import com.ebusato.connection.pool.benchmark.model.Person;
import com.ebusato.connection.pool.benchmark.service.AddressService;
import com.ebusato.connection.pool.benchmark.service.PersonService;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class BenchmarkTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private AddressService addressService;

    @Autowired
    protected DataSource dataSource;

    @Value("${person.table.size}")
    private Integer personTableSize;

    @Value("${person.address.size}")
    private Integer personAddressSize;

    @Value("${concurrent.clients}")
    private Integer concurrentClients;

    protected static final Logger LOGGER = LoggerFactory.getLogger(BenchmarkTest.class);

    @Before
    public void initialize() {
        LOGGER.info("database setup initialized...");
        LOGGER.info("using [{}]", dataSource.getClass().getName());
        this.warmUp();
        this.setupDataSource();
        this.cleanDatabase();
        this.setupData();
    }

    private void warmUp() {
        try {
            // forces internal pool creation
            this.dataSource.getLogWriter();
        } catch (SQLException e) {
            // hikari doesn't like it :(
            //throw new RuntimeException(e);
        }
    }


    private void cleanDatabase() {
        long startTime = System.currentTimeMillis();
        LOGGER.info("cleaning database...");
        this.personService.truncate();
        LOGGER.info("database cleaning up finished! took [{}] ms", System.currentTimeMillis() - startTime);

    }

    private void setupData() {
        long startTime = System.currentTimeMillis();
        LOGGER.info("saving {} person entries into database with {} adresses each...", personTableSize, personAddressSize);
        IntStream.rangeClosed(1, personTableSize).forEach(i -> {
            Person person = new Person(String.format("person %d",i));
            List<Address> addresses = IntStream.rangeClosed(1, personAddressSize)
                    .mapToObj(j -> new Address(person.getName().concat(" ").concat(String.format("address %d", j))))
                    .peek(a -> a.setPerson(person))
                    .collect(Collectors.toList());
            person.setAddresses(addresses);
            personService.save(person);
        });
        LOGGER.info("database setup finished! took [{}] ms", System.currentTimeMillis() - startTime);
    }

    public void execute() {
        LOGGER.info("simulating {} concurrent clients running queries...", concurrentClients);
        long startTime = System.currentTimeMillis();
        final List<CompletableFuture<?>> futures = new ArrayList<>();
        IntStream.rangeClosed(0, concurrentClients).forEach(i -> {
            futures.add(CompletableFuture.supplyAsync(() -> personService.list()));
            futures.add(CompletableFuture.supplyAsync(() -> addressService.list()));
            futures.add(CompletableFuture.supplyAsync(() -> personService.findByName(String.format("person %d",personTableSize))));
            futures.add(CompletableFuture.supplyAsync(() -> addressService.findByStreet(String.format("person %d address %d",1,personTableSize))));
        });
        futures.forEach(completableFuture -> {
            try {
                completableFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.error("error reading results!", e);
            }
        });
        LOGGER.info("test finished! took [{}] ms", System.currentTimeMillis() - startTime);
    }

    abstract void setupDataSource();
}
