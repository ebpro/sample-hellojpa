package fr.univtln.bruno.demos.jpa.hello;

import fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_onetomany_a.Line;
import fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_onetomany_a.Order;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

 class OnetoManyATest {
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
     void testOneToManyRelationship() {
        Order order1 = new Order()
                .addLine(Line.of("Pen", 1.0))
                .addLine(Line.of("Paper", 5.0))
                .addLine(Line.of("Scissor", 8.0));
        Order order2 = new Order()
                .addLine(Line.of("Car", 20000))
                .addLine(Line.of("Moto", 8000));

        entityManager.persist(order1);
        entityManager.persist(order2);

        // Flush and clear the persistence context to force a database round trip
        entityManager.flush();
        entityManager.clear();

        // Fetch the persisted order from the database
        List fetchedOrder = entityManager.createQuery("select o FROM OrderOneToManyA  o").getResultList();

        // Assert that the fetched order list is not empty
        assertFalse(fetchedOrder.isEmpty());
    }
}