package com.ebusato.connection.pool.benchmark.application;

import com.ebusato.connection.pool.benchmark.config.DefaultDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ApplicationConfig.class, DefaultDataSourceConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
    }
}