package fr.univtln.bruno.demos.jpa.hello.samples.ex_hook;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerHookTest {
    public static void main(String[] args) {

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa-pu")) {
            try (EntityManager entityManager = emf.createEntityManager()) {

                entityManager.getTransaction().begin();
                Customer customer = Customer.of("Peter");
                log.info(customer.toString());
                entityManager.persist(customer);
                customer.setName("Pierre");
                entityManager.getTransaction().commit();
                try (EntityManager anotherEM = emf.createEntityManager()) {
                log.info("FOUND " + anotherEM.find(Customer.class, 1L));
                }
                entityManager.getTransaction().begin();
                entityManager.remove(customer);
                entityManager.getTransaction().commit();
            }
        }
    }
}
