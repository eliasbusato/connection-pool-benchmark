# connection-pool-benchmark
## running tests

#### Apache DBCP2
```mvn -Dtest=ApacheDBCP2BenchmarkTest test```

#### Hikari CP

```mvn -Dtest=HikariBenchmarkTest test```

#### Hibernate C3P0

```mvn -Dtest=HibernateC3P0BenchmarkTest test```

#### Tomcat JDBC

```mvn -Dtest=TomcatJDBCBenchmarkTest test```
