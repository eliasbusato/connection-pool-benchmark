package com.ebusato.connection.pool.benchmark.test.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class TomcatJDBCDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.tomcat")
    public DataSource dataSource(DataSourceProperties props) {
        return props.initializeDataSourceBuilder().type(org.apache.tomcat.jdbc.pool.DataSource.class).build();
    }
}