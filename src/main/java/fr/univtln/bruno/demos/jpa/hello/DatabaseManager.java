package fr.univtln.bruno.demos.jpa.hello;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DatabaseManager {

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY;

    static {
        Map<String, String> env = System.getenv();
        Map<String, Object> configOverrides = new HashMap<>();

        String[][] configVariables = {
                {"jakarta.persistence.jdbc.url", "jakarta.persistence.jdbc.url", "DATASOURCE_URL"},
                {"jakarta.persistence.jdbc.user", "jakarta.persistence.jdbc.user", "DATASOURCE_USERNAME"},
                {"jakarta.persistence.jdbc.password", "jakarta.persistence.jdbc.password", "DATASOURCE_PASSWORD"}
        };

        for (String[] config : configVariables) {
            String property = System.getProperty(config[1]);
            if (!(property == null || property.isBlank())) {
                log.info("Override JPA config {} with system property {}", config[0], property);
                configOverrides.put(config[0], System.getProperty(config[1]));
            } else if (env.containsKey("DATASOURCE_URL")) {
                log.info("Override JPA config {} with env variable {}", config[0], env.get(config[2]));
                configOverrides.put(config[0], env.get(config[2]));
            }
        }

        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hellojpa-pu", configOverrides);
    }

    private DatabaseManager() {
    }

}
