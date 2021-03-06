package com.ebusato.connection.pool.benchmark.test.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class HibernateC3P0DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.c3p0")
    public DataSource dataSource(DataSourceProperties props) {
        return props.initializeDataSourceBuilder().type(ComboPooledDataSource.class).build();
    }
}