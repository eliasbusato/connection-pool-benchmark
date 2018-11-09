package com.ebusato.connection.pool.benchmark.application;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackageClasses = com.ebusato.connection.pool.benchmark.model._PackageMarker.class)
@ComponentScan(basePackageClasses = com.ebusato.connection.pool.benchmark.service._PackageMarker.class)
@EnableJpaRepositories(basePackageClasses = com.ebusato.connection.pool.benchmark.repository._PackageMarker.class)
public class ApplicationConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }
}
