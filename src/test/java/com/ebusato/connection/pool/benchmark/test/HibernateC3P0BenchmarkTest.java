package com.ebusato.connection.pool.benchmark.test;

import com.ebusato.connection.pool.benchmark.application.ApplicationConfig;
import com.ebusato.connection.pool.benchmark.test.config.HibernateC3P0DataSourceConfig;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationConfig.class, HibernateC3P0DataSourceConfig.class})
public class HibernateC3P0BenchmarkTest extends BenchmarkTest {

    @Test
    public void runTest() {
        super.execute();
    }

    @Override
    void setupDataSource() {
        ComboPooledDataSource ds = ComboPooledDataSource.class.cast(super.dataSource);
        LOGGER.info("minimum size: {}", ds.getMinPoolSize());
        LOGGER.info("maximum size: {}", ds.getMaxPoolSize());
    }
}