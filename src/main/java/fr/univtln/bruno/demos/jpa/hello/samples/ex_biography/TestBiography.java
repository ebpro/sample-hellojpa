package fr.univtln.bruno.demos.jpa.hello.samples.ex_biography;

import fr.univtln.bruno.demos.jpa.hello.DatabaseManager;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestBiography {
    public static void main(String[] args) {
        try (EntityManager entityManager = DatabaseManager.getInstance().getEntityManagerFactory().createEntityManager()) {
            entityManager.getTransaction().begin();
            Customer customer = Customer.of("Jim");
            customer.setBiography(Biography.builder().brief("bla").extended("bla bla").build());
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.error("Failed to execute transaction", e);
        }
    }
}
