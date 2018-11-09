package com.ebusato.connection.pool.benchmark.test;

import com.ebusato.connection.pool.benchmark.application.ApplicationConfig;
import com.ebusato.connection.pool.benchmark.test.config.TomcatJDBCDataSourceConfig;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationConfig.class, TomcatJDBCDataSourceConfig.class})
public class TomcatJDBCBenchmarkTest extends BenchmarkTest {

    @Test
    @Sql("/truncate.sql")
    public void runTest() {
        super.execute();
    }

    @Override
    void logDataSourceInfo() {
        DataSource ds = DataSource.class.cast(super.dataSource);
        LOGGER.info("minimum idle: {}", ds.getMinIdle());
        LOGGER.info("maximum idle: {}", ds.getMaxIdle());
    }
}