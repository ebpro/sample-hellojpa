package fr.univtln.bruno.demos.jpa.hello.samples.ex_uuid;

import fr.univtln.bruno.demos.jpa.hello.DatabaseManager;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;

import java.util.stream.Stream;

@Slf4j
public class TestUUID {
    public static void main(String[] args) {
        try (EntityManager entityManager = DatabaseManager.getInstance().getEntityManagerFactory().createEntityManager()) {
            entityManager.getTransaction().begin();

            Faker faker = new Faker();

            Stream.generate(() -> {
                String fullname = faker.name().fullName();
                return Customer.of(fullname);
            }).limit(5).forEach(entityManager::persist);

            entityManager.getTransaction().commit();

            entityManager.createQuery("""
                    SELECT c 
                    FROM CustomerUUID c""")
                    .setMaxResults(5)
                    .getResultStream()
                    .forEach(o->log.info("{}",o));
        } catch (Exception e) {
            log.error("Failed to execute transaction", e);
        }
    }
}
