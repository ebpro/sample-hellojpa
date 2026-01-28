package fr.univtln.bruno.demos.jpa.hello;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@Slf4j
public class DatabaseManager {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;

    static {
        Properties fileProps = new Properties();
        try (InputStream input = DatabaseManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null)
                fileProps.load(input);
        } catch (IOException ex) {
            log.error("Could not load config.properties", ex);
        }

        Properties overrides = new Properties();

        // Define the mapping between JPA keys and Environment variables
        Map<String, String> propertyMap = Map.of(
                "jakarta.persistence.jdbc.url", "DB_URL",
                "jakarta.persistence.jdbc.user", "DB_USERNAME",
                "jakarta.persistence.jdbc.password", "DB_PASSWORD");

        propertyMap.forEach((jpaKey, envVar) -> {
            String sysProp = envVar.toLowerCase().replace("_", ".");

            // Priority: Environment Variable > System Property (-D) > config.properties
            String value = Optional.ofNullable(System.getenv(envVar))
                    .orElse(Optional.ofNullable(System.getProperty(sysProp))
                            .orElse(fileProps.getProperty(sysProp)));

            if (value != null) {
                overrides.setProperty(jpaKey, value);

                // Map to Hibernate-specific Hikari settings to ensure the pool uses the
                // overrides
                if (jpaKey.endsWith("url"))
                    overrides.setProperty("hibernate.hikari.jdbcUrl", value);
                if (jpaKey.endsWith("user"))
                    overrides.setProperty("hibernate.hikari.username", value);
                if (jpaKey.endsWith("password"))
                    overrides.setProperty("hibernate.hikari.password", value);
            }
        });

        // Set the pool provider
        overrides.setProperty("hibernate.connection.provider_class",
                "org.hibernate.hikaricp.internal.HikariCPConnectionProvider");

        // Pool defaults
        overrides.putIfAbsent("hibernate.hikari.maximumPoolSize", "10");
        overrides.putIfAbsent("hibernate.hikari.connectionTimeout", "30000");

        log.info("Starting JPA with URL: {}", overrides.getProperty("jakarta.persistence.jdbc.url"));

        try {
            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hellojpaPU", overrides);
        } catch (Exception e) {
            log.error("Failed to create EntityManagerFactory", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }

    private DatabaseManager() {
    }
}
