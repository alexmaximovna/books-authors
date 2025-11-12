package com.books.authors;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.test.support.TestPropertyProvider;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.PostgreSQLR2DBCDatabaseContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostgresTestPropertiesProvider implements TestPropertyProvider {

    private static final String PG_DATABASE = "books-authors";

    @Container
    protected static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15.2"))
            .withDatabaseName(PG_DATABASE)
            .withUsername("postgres")
            .withPassword("postgres")
            .withInitScript("init.sql")
            .withExposedPorts(PostgreSQLContainer.POSTGRESQL_PORT);

    @Override
    public @NonNull Map<String, String> getProperties() {
        new PostgreSQLR2DBCDatabaseContainer(postgres).start();
        String r2dbcUrl = String.format("r2dbc:postgresql://%s:%s/%s", postgres.getHost(), postgres.getFirstMappedPort(), PG_DATABASE);
        return new HashMap<>() {

            @Serial
            private static final long serialVersionUID = 3270234047605089295L;

            {
                put("r2dbc.datasources.default.dialect", "postgres");
                put("r2dbc.datasources.default.db-type", "postgresql");
                put("r2dbc.datasources.default.url", r2dbcUrl);
                put("r2dbc.datasources.default.username", postgres.getUsername());
                put("r2dbc.datasources.default.password", postgres.getPassword());

                put("datasources.default.driver-class-name", "org.postgresql.Driver");
                put("datasources.default.url", postgres.getJdbcUrl());
                put("datasources.default.username", postgres.getUsername());
                put("datasources.default.password", postgres.getPassword());
            }
        };
    }
}
