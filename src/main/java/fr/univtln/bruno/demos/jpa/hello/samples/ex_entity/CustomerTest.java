package fr.univtln.bruno.demos.jpa.hello.samples.ex_entity;

import fr.univtln.bruno.demos.jpa.hello.DatabaseManager;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerTest {
    public static void main(String[] args) {
        CustomerGenerator.generateCustomer(1000);

        try (EntityManager anotherEntityManager = DatabaseManager.ENTITY_MANAGER_FACTORY.createEntityManager()) {
            log.info("{}",anotherEntityManager.find(Customer.class, 1L));
        }

    }
}
