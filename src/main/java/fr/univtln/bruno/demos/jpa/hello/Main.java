package fr.univtln.bruno.demos.jpa.hello;

import fr.univtln.bruno.demos.jpa.hello.samples.ex_entity.Customer;
import jakarta.persistence.EntityManager;
import lombok.extern.java.Log;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Main class.
 */
@Log
public class Main {


    /**
     * Main method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try (EntityManager entityManager = DatabaseManager.ENTITY_MANAGER_FACTORY.createEntityManager()) {
            entityManager.getTransaction().begin();
            Customer customer = Customer.of("pierre.durand@ici.fr");
            customer.setFirstname("Pierre");
            customer.setLastname("Durand");
            entityManager.persist(customer);
            log.info(customer.toString());
            customer.setBirthDate(LocalDate.of(2012, 03, 05));
            entityManager.merge(customer);
            log.info(customer.toString());
            entityManager.getTransaction().commit();
        }


    }
}

