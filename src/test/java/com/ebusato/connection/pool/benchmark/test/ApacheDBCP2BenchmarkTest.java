package com.ebusato.connection.pool.benchmark.test;

import com.ebusato.connection.pool.benchmark.application.ApplicationConfig;
import com.ebusato.connection.pool.benchmark.test.config.ApacheDBCP2DataSourceConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationConfig.class, ApacheDBCP2DataSourceConfig.class})
public class ApacheDBCP2BenchmarkTest extends BenchmarkTest {

    @Test
    public void runTest() {
        super.execute();
    }

    @Override
    void setupDataSource() {
        BasicDataSource ds = BasicDataSource.class.cast(super.dataSource);
        ds.setSoftMinEvictableIdleTimeMillis(MINUTES.toMillis(10));
        ds.setTimeBetweenEvictionRunsMillis(SECONDS.toMillis(30));

        ds.setDefaultAutoCommit(false);
        ds.setRollbackOnReturn(true);
        ds.setEnableAutoCommitOnReturn(false);
        ds.setTestOnBorrow(true);
        ds.setCacheState(true);
        ds.setFastFailValidation(true);

        LOGGER.info("test on borrow: {}", ds.getTestOnBorrow());
        LOGGER.info("rollback on return: {}", ds.getRollbackOnReturn());
        LOGGER.info("enable auto-commit on return: {}", ds.getEnableAutoCommitOnReturn());
        LOGGER.info("cache state: {}", ds.getCacheState());
        LOGGER.info("fast fail validation: {}", ds.getFastFailValidation());
        LOGGER.info("initial size: {}", ds.getInitialSize());
        LOGGER.info("maximum total: {}", ds.getMaxTotal());
        LOGGER.info("maximum wait millis: {}", ds.getMaxWaitMillis());
        LOGGER.info("minimum idle: {}", ds.getMinIdle());
        LOGGER.info("maximum idle: {}", ds.getMaxIdle());

    }
}