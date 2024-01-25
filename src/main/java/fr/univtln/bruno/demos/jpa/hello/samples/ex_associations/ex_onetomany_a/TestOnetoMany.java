package fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_onetomany_a;

import fr.univtln.bruno.demos.jpa.hello.DatabaseManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestOnetoMany {
    public static void main(String[] args) {
        EntityManagerFactory emf = DatabaseManager.getInstance().getEntityManagerFactory();

        Order order1 = new Order()
                .addLine(Line.of("Pen", 1.0))
                .addLine(Line.of("Paper", 5.0))
                .addLine(Line.of("Scissor", 8.0));
        Order order2 = new Order()
                .addLine(Line.of("Car", 20000))
                .addLine(Line.of("Moto", 8000));

        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(order1);
            entityManager.persist(order2);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.error("Failed to execute transaction", e);
        }

        try (EntityManager entityManager = emf.createEntityManager()) {
            log.info("{}",entityManager.find(Order.class, 1L));
        } catch (Exception e) {
            log.error("Failed to execute query", e);
        }

    }
}
