package com.ebusato.connection.pool.benchmark.test;

import com.ebusato.connection.pool.benchmark.application.ApplicationConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationConfig.class, HikariBenchmarkTest.class})
public class HikariBenchmarkTest extends BenchmarkTest {

    @Test
    public void runTest() {
        super.execute();
    }

    @Override
    void setupDataSource() {
        HikariDataSource ds = HikariDataSource.class.cast(super.dataSource);
        LOGGER.info("connection time out: {}", ds.getConnectionTimeout());
        LOGGER.info("auto commit: {}", ds.isAutoCommit());
        LOGGER.info("minimum idle: {}", ds.getMinimumIdle());
        LOGGER.info("maximum size: {}", ds.getMaximumPoolSize());
    }
}