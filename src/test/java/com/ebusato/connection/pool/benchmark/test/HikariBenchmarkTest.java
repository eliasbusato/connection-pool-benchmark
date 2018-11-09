package com.ebusato.connection.pool.benchmark.test;

import com.ebusato.connection.pool.benchmark.application.ApplicationConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationConfig.class, HikariBenchmarkTest.class})
public class HikariBenchmarkTest extends BenchmarkTest {

    @Test
    @Sql("/truncate.sql")
    public void runTest() {
        super.execute();
    }

    @Override
    void logDataSourceInfo() {
        HikariDataSource ds = HikariDataSource.class.cast(super.dataSource);
        LOGGER.info("minimum idle: {}", ds.getMinimumIdle());
        LOGGER.info("maximum size: {}", ds.getMaximumPoolSize());
    }
}