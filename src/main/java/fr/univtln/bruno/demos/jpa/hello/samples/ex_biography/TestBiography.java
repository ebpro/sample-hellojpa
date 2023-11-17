package fr.univtln.bruno.demos.jpa.hello.samples.ex_biography;

import fr.univtln.bruno.demos.jpa.hello.DatabaseManager;
import jakarta.persistence.EntityManager;

public class TestBiography {
    public static void main(String[] args) {
        try (EntityManager entityManager = DatabaseManager
                .ENTITY_MANAGER_FACTORY.createEntityManager()) {
            entityManager.getTransaction().begin();
            Customer customer = Customer.of("Jim");
            customer.setBiography(Biography.builder().brief("bla").extended("bla bla").build());
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        }
    }
}
