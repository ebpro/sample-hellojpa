package fr.univtln.bruno.demos.jpa.hello;

import fr.univtln.bruno.demos.jpa.hello.samples.ex_biography.Biography;
import fr.univtln.bruno.demos.jpa.hello.samples.ex_biography.Customer;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

 class BiographyTest {
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
     void testPersistCustomerWithBiography() {
        Customer customer = Customer.of("Jim");
        customer.setBiography(Biography.builder().brief("bla").extended("bla bla").build());
        entityManager.persist(customer);

        // Flush and clear the persistence context to force a database round trip
        entityManager.flush();
        entityManager.clear();

        // Fetch the persisted customer from the database
        Customer fetchedCustomer = entityManager.find(Customer.class, customer.getId());

        // Assert that the fetched customer is not null
        assertNotNull(fetchedCustomer);
        // Assert that the fetched customer's biography is not null
        assertNotNull(fetchedCustomer.getBiography());
    }
}