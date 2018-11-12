package com.ebusato.connection.pool.benchmark.test;

import com.ebusato.connection.pool.benchmark.application.ApplicationConfig;
import com.ebusato.connection.pool.benchmark.test.config.TomcatJDBCDataSourceConfig;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.apache.tomcat.jdbc.pool.PooledConnection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationConfig.class, TomcatJDBCDataSourceConfig.class})
public class TomcatJDBCBenchmarkTest extends BenchmarkTest {

    @Test
    public void runTest() {
        super.execute();
    }

    @Override
    void setupDataSource() {
        DataSource ds = DataSource.class.cast(super.dataSource);

        PoolConfiguration props = ds.getPoolProperties();

        props.setDefaultAutoCommit(false);

        props.setRollbackOnReturn(true);
        props.setUseDisposableConnectionFacade(true);
        props.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState"); //;org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        props.setTestOnBorrow(true);
        props.setValidationInterval(250);
        props.setValidator((connection, validateAction) -> {
            try {
                return (validateAction == PooledConnection.VALIDATE_BORROW ? connection.isValid(0) : true);
            }
            catch (SQLException e)
            {
                return false;
            }
        });

        LOGGER.info("test on borrow: {}", ds.isTestOnBorrow());
        LOGGER.info("default auto commit: {}", ds.getDefaultAutoCommit());
        LOGGER.info("use disposable connection facade: {}", ds.getUseDisposableConnectionFacade());
        LOGGER.info("rollback on return: {}", ds.getRollbackOnReturn());
        LOGGER.info("minimum idle: {}", ds.getMinIdle());
        LOGGER.info("maximum idle: {}", ds.getMaxIdle());
    }
}