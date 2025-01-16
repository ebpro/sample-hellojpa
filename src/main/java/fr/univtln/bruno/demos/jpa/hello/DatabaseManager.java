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
                throw new IllegalStateException("config.properties not found");
            }
            configfileProperties.load(input);
        } catch (IOException ex) {
            log.error("Sorry, unable to find config.properties: {}", ex.getMessage());
            throw new IllegalStateException("Failed to load config.properties", ex);
        }

        //Override the jakarta persistence properties from the environment, system properties or config file.
        Properties configOverrides = new Properties();
        Map.of("jakarta.persistence.jdbc.url", "DB_URL",
                        "jakarta.persistence.jdbc.user", "DB_USERNAME",
                        "jakarta.persistence.jdbc.password", "DB_PASSWORD")
                .forEach((k,v)->{
                    final String property = v.toLowerCase().replace("_", ".");
                    log.debug("looking for property {} in variable {} or in property {}", k, v, property);
                    log.debug("System.getenv({}) {}", v, Optional.ofNullable(System.getenv(v)).isPresent()? "found" : "not found");
                    log.debug("System.getProperty({}) {}", property, Optional.ofNullable(System.getProperty(property)).isPresent()? "found" : "not found");
                    configOverrides.setProperty(k, Optional.ofNullable(System.getenv(v))
                            .orElse(Optional.ofNullable(System.getProperty(property))
                                    .orElse(configfileProperties.getProperty(property))));
                });

        log.info("Connection to {} as {}", configOverrides.getProperty("jakarta.persistence.jdbc.url"), configOverrides.getProperty("jakarta.persistence.jdbc.user"));

        try {
            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hellojpaPU", configOverrides);
        } catch (Exception e) {
            log.error("Failed to create EntityManagerFactory", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }
}