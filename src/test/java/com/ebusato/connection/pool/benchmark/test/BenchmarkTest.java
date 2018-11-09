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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BenchmarkTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private DataSource dataSource;

    @Value("${person.table.size}")
    private Integer personTableSize;

    @Value("${person.address.size}")
    private Integer personAddressSize;

    private static final Logger LOGGER = LoggerFactory.getLogger(BenchmarkTest.class);

    @Before
    public void initialize() {
        long startTime = System.currentTimeMillis();
        LOGGER.info("database setup initialized...");
        LOGGER.info("using [{}]", dataSource.getClass().getName());
        LOGGER.info("saving {} person entries into database with {} adresses each...", personTableSize, personAddressSize);
        IntStream.rangeClosed(1, personTableSize).forEach(i -> {
            Person person = new Person(String.format("Person %d",i));
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
        long startTime = System.currentTimeMillis();
        final List<CompletableFuture<?>> futures = new ArrayList<>();
        IntStream.rangeClosed(0, 100).forEach(i -> {
            futures.add(CompletableFuture.supplyAsync(() -> personService.list()));
            futures.add(CompletableFuture.supplyAsync(() -> addressService.list()));
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
}
