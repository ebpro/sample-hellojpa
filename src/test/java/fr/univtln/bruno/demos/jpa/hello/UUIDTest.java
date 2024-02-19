package fr.univtln.bruno.demos.jpa.hello;

import fr.univtln.bruno.demos.jpa.hello.samples.ex_uuid.Customer;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
 class UUIDTest {
    private EntityManager entityManager;
    private Faker faker;

    @BeforeEach
    public void setUp() {
        entityManager = DatabaseManager.getEntityManagerFactory().createEntityManager();
        faker = new Faker();
    }

    @AfterEach
    public void tearDown() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

    @Test
     void persistAndFetchCustomers() {
        entityManager.getTransaction().begin();

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
                .forEach(o -> {
                    log.info("{}", o);
                    assertNotNull(o);
                });
    }
}