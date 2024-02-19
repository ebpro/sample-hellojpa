package fr.univtln.bruno.demos.jpa.hello;

import fr.univtln.bruno.demos.jpa.hello.samples.ex_entity.Customer;
import fr.univtln.bruno.demos.jpa.hello.samples.ex_entity.CustomerGenerator;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


 class EntityTest {
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
     void testCustomerGenerationAndFetch() {
        CustomerGenerator.generateCustomer(1000);

        // Fetch the persisted customer from the database
        List<Customer> fetchedCustomer = entityManager.createQuery("SELECT c from Customer c", Customer.class).getResultList();

        // Assert that the fetched customer is not null
        assertFalse(fetchedCustomer.isEmpty());
    }
}