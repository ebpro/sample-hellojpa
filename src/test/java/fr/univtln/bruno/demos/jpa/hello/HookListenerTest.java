package fr.univtln.bruno.demos.jpa.hello;

import fr.univtln.bruno.demos.jpa.hello.samples.ex_hook.CustomerWithListener;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
 class HookListenerTest {
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
     void testCustomerLifecycleWithListener() {
        CustomerWithListener customer = CustomerWithListener.of("Peter");
        log.info(customer.toString());
        entityManager.persist(customer);
        customer.setName("Pierre");

        // Flush and clear the persistence context to force a database round trip
        entityManager.flush();
        entityManager.clear();

        // Fetch the persisted customer from the database
        CustomerWithListener fetchedCustomer = entityManager.find(CustomerWithListener.class, customer.getId());

        // Assert that the fetched customer is not null
        assertNotNull(fetchedCustomer);

        entityManager.remove(fetchedCustomer);
    }
}