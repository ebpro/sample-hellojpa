package fr.univtln.bruno.demos.jpa.hello.samples.ex_simple;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerTest {
    public static void main(String[] args) {

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa-pu")) {
            try (EntityManager entityManager = emf.createEntityManager()) {
                entityManager.getTransaction().begin();

                Customer customer = Customer.of("pierre.durand@ici.fr");
                log.info("BEFORE PERSIST {}", customer.toString());

                entityManager.persist(customer);
                log.info("AFTER PERSIST: {}", customer);

                customer.setName("Pierre");
                log.info("AFTER CHANGE: {}", customer);

                entityManager.getTransaction().commit();

                try (EntityManager anotherEM = emf.createEntityManager()) {
                    log.info("Search Id 1: {}", anotherEM.find(Customer.class, 1L));
                }

                entityManager.getTransaction().begin();
                entityManager.remove(customer);
                entityManager.getTransaction().commit();

                try (EntityManager anotherEntityManager = emf.createEntityManager()) {
                    log.info("After remove Search Id 1: {}", anotherEntityManager.find(Customer.class, 1L));
                }

            }
        }
    }
}
