package fr.univtln.bruno.demos.jpa.hello;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class DatabaseManager {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private static final DatabaseManager INSTANCE;

    static {
        INSTANCE = new DatabaseManager();
        Properties configOverrides = new Properties();

        configOverrides.setProperty("jakarta.persistence.jdbc.url", System.getProperty("jakarta.persistence.jdbc.url", System.getenv("DATASOURCE_URL")));
        configOverrides.setProperty("jakarta.persistence.jdbc.user", System.getProperty("jakarta.persistence.jdbc.user", System.getenv("DATASOURCE_USERNAME")));
        configOverrides.setProperty("jakarta.persistence.jdbc.password", System.getProperty("jakarta.persistence.jdbc.password", System.getenv("DATASOURCE_PASSWORD")));

        try {
            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hellojpa-pu", configOverrides);
        } catch (Exception e) {
            log.error("Failed to create EntityManagerFactory", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    private DatabaseManager() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static DatabaseManager getInstance() {
        return INSTANCE;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }
}