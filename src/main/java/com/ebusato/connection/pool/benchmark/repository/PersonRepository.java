package com.ebusato.connection.pool.benchmark.repository;

import com.ebusato.connection.pool.benchmark.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
