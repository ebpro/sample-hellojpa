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

        // 1. Récupération des variables d'environnement avec valeurs par défaut
        String dbName = getSetting("DB_NAME", "notebook-db", fileProps);
        String dbUser = getSetting("DB_USERNAME", "dba", fileProps);
        String dbPass = getSetting("DB_PASSWORD", "secretsecret", fileProps);

        // Construction de l'URL par défaut si DB_URL n'est pas définie
        String defaultUrl = "jdbc:postgresql://localhost/" + dbName;
        String dbUrl = getSetting("DB_URL", defaultUrl, fileProps);

        log.info("Database settings: URL={}, User={}", dbUrl, dbUser);

        // 2. Mapping vers les propriétés Jakarta Persistence
        Map<String, String> jpaProperties = Map.of(
                "jakarta.persistence.jdbc.url", dbUrl,
                "jakarta.persistence.jdbc.user", dbUser,
                "jakarta.persistence.jdbc.password", dbPass);

        jpaProperties.forEach((jpaKey, value) -> {
            overrides.setProperty(jpaKey, value);

            // Mapping vers Hibernate HikariCP
            if (jpaKey.endsWith("url"))
                overrides.setProperty("hibernate.hikari.jdbcUrl", value);
            if (jpaKey.endsWith("user"))
                overrides.setProperty("hibernate.hikari.username", value);
            if (jpaKey.endsWith("password"))
                overrides.setProperty("hibernate.hikari.password", value);
        });

        // 3. Configuration du Pool (HikariCP)
        overrides.setProperty("hibernate.connection.provider_class",
                "org.hibernate.hikaricp.internal.HikariCPConnectionProvider");
        overrides.putIfAbsent("hibernate.hikari.maximumPoolSize", "10");
        overrides.putIfAbsent("hibernate.hikari.connectionTimeout", "30000");

        log.info("Starting JPA with URL: {} and User: {}", dbUrl, dbUser);

        try {
            // "hellojpaPU" doit correspondre au name dans votre persistence.xml
            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hellojpaPU", overrides);
        } catch (Exception e) {
            log.error("Failed to create EntityManagerFactory", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Helper pour respecter la hiérarchie :
     * 1. Variable d'environnement (ENV_VAR)
     * 2. Propriété Système Java (env.var)
     * 3. Fichier config.properties
     * 4. Valeur par défaut
     */
    private static String getSetting(String envVar, String defaultValue, Properties fileProps) {
        String sysProp = envVar.toLowerCase().replace("_", ".");
        return Optional.ofNullable(System.getenv(envVar))
                .orElse(Optional.ofNullable(System.getProperty(sysProp))
                        .orElse(fileProps.getProperty(sysProp, defaultValue)));
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }

    private DatabaseManager() {
    }
}
