package fr.univtln.bruno.demos.jpa.hello;

import fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_manytomany.Address;
import fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_manytomany.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
 class ManytoManyTest {
    private EntityManager entityManager;
    private Faker faker;

    @BeforeEach
    public void setUp() {
        entityManager = DatabaseManager.getEntityManagerFactory().createEntityManager();
        faker = new Faker();
    }

    @AfterEach
    public void tearDown() {
        entityManager.close();
    }

    @Test
     void testManyToManyRelationship() {
        int nbAddresses = 3;
        int nbCustomers = 10;

        entityManager.getTransaction().begin();

        TypedQuery<Address> query = entityManager.createQuery("""
                SELECT a
                FROM Address a
                ORDER BY random()""", Address.class);

        Stream.generate(faker.address()::fullAddress)
                .map(Address::of)
                .limit(nbAddresses)
                .forEach(entityManager::persist);

        Stream.generate(faker.name()::fullName)
                .map(Customer::of)
                .limit(nbCustomers)
                .forEach(c -> query.setMaxResults(ThreadLocalRandom.current().nextInt(3) + 1)
                        .getResultList()
                        .stream()
                        .map(a -> {
                            c.getPlaces().add(a);
                            return c;
                        })
                        .forEach(entityManager::persist));

        entityManager.getTransaction().commit();

        // Fetch the persisted address from the database
        Address fetchedAddress = entityManager.find(Address.class, 1L);

        // Assert that the fetched address is not null
        assertNotNull(fetchedAddress);

        // Assert that the occupants of the fetched address are not null
        assertNotNull(fetchedAddress.getOccupants());
    }
}