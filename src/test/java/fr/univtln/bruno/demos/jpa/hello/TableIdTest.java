package fr.univtln.bruno.demos.jpa.hello;

import fr.univtln.bruno.demos.jpa.hello.samples.ex_ids.ex_tableid.Customer;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

 class TableIdTest {
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        entityManager = DatabaseManager.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
    }

    @AfterEach
    public void tearDown() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }

    @Test
     void persistCustomers() {
        Customer customer1 = Customer.builder().name("Pierre").build();
        Customer customer2 = Customer.builder().name("Paul").build();

        entityManager.persist(customer1);
        entityManager.persist(customer2);

        // Flush and clear the persistence context to force a database round trip
        entityManager.flush();
        entityManager.clear();

        // Fetch the persisted customers from the database
        Customer fetchedCustomer1 = entityManager.find(Customer.class, customer1.getId());
        Customer fetchedCustomer2 = entityManager.find(Customer.class, customer2.getId());

        // Assert that the fetched customers are not null
        assertNotNull(fetchedCustomer1);
        assertNotNull(fetchedCustomer2);
    }
}