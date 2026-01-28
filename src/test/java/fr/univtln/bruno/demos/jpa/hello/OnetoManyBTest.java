package fr.univtln.bruno.demos.jpa.hello;

import fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_onetomany_b.Line;
import fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_onetomany_b.Order;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

 class OnetoManyBTest {
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        System.setProperty("db.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        System.setProperty("db.username", "sa");
        System.setProperty("db.password", "");
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
     void testOneToManyRelationship() {
        Order order1 = Order.builder()
                .line(Line.of("Pen", 1.0))
                .line(Line.of("Paper", 5.0))
                .build();
        Order order2 = Order.builder()
                .line(Line.of("Car", 20000))
                .line(Line.of("Moto", 8000))
                .build();

        entityManager.persist(order1);
        entityManager.persist(order2);

        // Flush and clear the persistence context to force a database round trip
        entityManager.flush();
        entityManager.clear();

        // Fetch the persisted orders from the database
        Order fetchedOrder1 = entityManager.find(Order.class, order1.getId());
        Order fetchedOrder2 = entityManager.find(Order.class, order2.getId());

        // Assert that the fetched orders are not null
        assertNotNull(fetchedOrder1);
        assertNotNull(fetchedOrder2);

        // Assert that the total number of lines is 4
        assertEquals(4, fetchedOrder1.getLines().size() + fetchedOrder2.getLines().size());
    }
}
