package fr.univtln.bruno.demos.jpa.hello.samples.ex_ids.ex_tableid;

import fr.univtln.bruno.demos.jpa.hello.DatabaseManager;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {
    public static void main(String[] args) {
            try (EntityManager entityManager = DatabaseManager.getInstance().getEntityManagerFactory().createEntityManager()) {
                entityManager.getTransaction().begin();
                entityManager.persist(Customer.builder().name("Pierre").build());
                entityManager.persist(Customer.builder().name("Paul").build());
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                log.error("Failed to execute transaction", e);
            }
    }
}
