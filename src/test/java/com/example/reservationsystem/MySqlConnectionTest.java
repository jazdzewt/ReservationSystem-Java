package com.example.reservationsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("mysql-test") // Use MySQL test profile
public class MySqlConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testMySqlConnectionIsEstablished() throws Exception {
        // Test that we can get a connection
        try (Connection connection = dataSource.getConnection()) {
            assertThat(connection).isNotNull();
            assertThat(connection.isValid(2)).isTrue();
        }
    }

    @Test
    void testMySqlDatabaseProduct() throws Exception {
        // Test that we're connected to MySQL
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            String productName = metaData.getDatabaseProductName();

            assertThat(productName).isEqualTo("MySQL");
            System.out.println("Connected to: " + productName);
            System.out.println("Database version: " + metaData.getDatabaseProductVersion());
        }
    }

    @Test
    void testMySqlVersion() {
        // Test MySQL-specific function
        String version = jdbcTemplate.queryForObject("SELECT VERSION()", String.class);
        System.out.println("MySQL Version: " + version);
    }

    @Test
    void testDatabaseName() {
        // Test current database name
        String dbName = jdbcTemplate.queryForObject("SELECT DATABASE()", String.class);
        assertThat(dbName).isNotNull();
        System.out.println("Current database: " + dbName);
    }

    @Test
    void testCanExecuteQueries() {
        // Test basic query execution
        String result = jdbcTemplate.queryForObject("SELECT 'MySQL Connection Successful'", String.class);
        assertThat(result).isEqualTo("MySQL Connection Successful");

        // Test current time (MySQL function)
        String currentTime = jdbcTemplate.queryForObject("SELECT NOW()", String.class);
        assertThat(currentTime).isNotNull();
        System.out.println("Current MySQL time: " + currentTime);
    }

    @Test
    void testTablesExist() {
        // Check if we can query system tables
        Integer tableCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = DATABASE()",
                Integer.class
        );
        assertThat(tableCount).isGreaterThanOrEqualTo(0);
        System.out.println("Tables in database: " + tableCount);
    }
}