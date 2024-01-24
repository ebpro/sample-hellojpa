package fr.univtln.bruno.demos.jpa.hello.samples.ex_entity;

import fr.univtln.bruno.demos.jpa.hello.DatabaseManager;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerTest {
    public static void main(String[] args) {
        try {
            CustomerGenerator.generateCustomer(1000);
        } catch (Exception e) {
            log.error("Failed to generate customers", e);
        }

        try (EntityManager anotherEntityManager = DatabaseManager.getInstance().getEntityManagerFactory().createEntityManager()) {
            log.info("{}",anotherEntityManager.find(Customer.class, 1L));
        } catch (Exception e) {
            log.error("Failed to execute query", e);
        }

    }
}
