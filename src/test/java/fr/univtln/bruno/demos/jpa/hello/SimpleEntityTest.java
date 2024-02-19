package fr.univtln.bruno.demos.jpa.hello;

import fr.univtln.bruno.demos.jpa.hello.samples.ex_simple.Customer;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
class SimpleEntityTest {
    private EntityManager entityManager;
    private long id;

    private Customer customer;


    @BeforeEach
    void setUp() {
        entityManager = DatabaseManager.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Customer customer = Customer.of("pierre.durand@ici.fr");
        entityManager.persist(customer);
        customer.setName("Pierre");
        entityManager.getTransaction().commit();

        id = customer.getId();
    }

    @AfterEach
    void tearDown() {
        entityManager.getTransaction().begin();
        Customer customer = entityManager.find(Customer.class, id);
        if (customer != null) entityManager.remove(customer);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    void testPersistAndFetchCustomer() {
        Customer expectedCustomer = Customer.of("pierre.durand@ici.fr");
        expectedCustomer.setName("Pierre");
        expectedCustomer.setId(id);
        try (EntityManager anotherEntityManager = DatabaseManager.getEntityManagerFactory().createEntityManager()) {
            Customer fetchedCustomer = anotherEntityManager.find(Customer.class, id);
            assertThat(fetchedCustomer)
                    .usingRecursiveComparison()
                    .isEqualTo(expectedCustomer);
        }
    }

    @Test
    void testRemoveCustomer() {

        try (EntityManager anotherEntityManager = DatabaseManager.getEntityManagerFactory().createEntityManager()) {
            anotherEntityManager.remove(anotherEntityManager.find(Customer.class, id));
            assertNull(anotherEntityManager.find(Customer.class, id));
        }
    }
}