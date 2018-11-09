package com.ebusato.connection.pool.benchmark.test;

import com.ebusato.connection.pool.benchmark.application.ApplicationConfig;
import com.ebusato.connection.pool.benchmark.test.config.ApacheDBCP2DataSourceConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationConfig.class, ApacheDBCP2DataSourceConfig.class})
public class ApacheDBCP2BenchmarkTest extends BenchmarkTest {



    @Test
    @Sql("/truncate.sql")
    public void runTest() {
        super.execute();
    }

    @Override
    void logDataSourceInfo() {
        BasicDataSource ds = BasicDataSource.class.cast(super.dataSource);
        LOGGER.info("minimum idle: {}", ds.getMinIdle());
        LOGGER.info("maximum idle: {}", ds.getMaxIdle());
    }
}