package fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_onetomany_a;

import fr.univtln.bruno.demos.jpa.hello.DatabaseManager;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestOnetoMany {
    public static void main(String[] args) {
        Order order1 = new Order()
                .addLine(Line.of("Pen", 1.0))
                .addLine(Line.of("Paper", 5.0))
                .addLine(Line.of("Scissor", 8.0));
        Order order2 = new Order()
                .addLine(Line.of("Car", 20000))
                .addLine(Line.of("Moto", 8000));

        try (EntityManager entityManager = DatabaseManager
                .ENTITY_MANAGER_FACTORY.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(order1);
            entityManager.persist(order2);
            entityManager.getTransaction().commit();
        }

        try (EntityManager entityManager = DatabaseManager
                .ENTITY_MANAGER_FACTORY.createEntityManager()) {
            log.info("{}",entityManager.find(Order.class, 1L));
        }


    }
}
