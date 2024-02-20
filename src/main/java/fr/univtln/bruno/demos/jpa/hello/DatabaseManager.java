package fr.univtln.bruno.demos.jpa.hello;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@Slf4j
public class DatabaseManager {

    private DatabaseManager() {
    }

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;

    static {

        //load a properties file from classpath
        Properties configfileProperties = new Properties();
        try (InputStream input = DatabaseManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                log.error("Sorry, unable to find config.properties");
                System.exit(1);
            }
            configfileProperties.load(input);
        } catch (IOException ex) {
            log.error("Sorry, unable to find config.properties: {}", ex.getMessage());
            System.exit(1);
        }

        //Override the jakarta persistence properties from the environment, system properties or config file.
        Properties configOverrides = new Properties();
        Map.of("jakarta.persistence.jdbc.url", "DB_URL",
                        "jakarta.persistence.jdbc.user", "DB_USERNAME",
                        "jakarta.persistence.jdbc.password", "DB_PASSWORD")
                .forEach((k,v)->configOverrides.setProperty(k, Optional.ofNullable(System.getenv(v))
                            .orElse(Optional.ofNullable(System.getProperty(v.toLowerCase().replace("_", ".")))
                                .orElse(configfileProperties.getProperty(v.toLowerCase().replace("_", "."))))));
        log.info("Connection to {} as {}", configOverrides.getProperty("jakarta.persistence.jdbc.url"), configOverrides.getProperty("jakarta.persistence.jdbc.user"));

        try {
            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hellojpa-pu", configOverrides);
        } catch (Exception e) {
            log.error("Failed to create EntityManagerFactory", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }
}