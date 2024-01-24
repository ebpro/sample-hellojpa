package fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_manytomany;

import fr.univtln.bruno.demos.jpa.hello.DatabaseManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Slf4j
public class TestManytoMany {
    public static void main(String[] args) {
        Faker faker = new Faker();

        int nbAddresses = 3;
        int nbCustomers = 10;

        try (EntityManager entityManager = DatabaseManager.getInstance().getEntityManagerFactory()
                .createEntityManager()) {
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
        } catch (Exception e) {
            log.error("Failed to execute transaction", e);
        }

        try (EntityManager entityManager = DatabaseManager.getInstance().getEntityManagerFactory().createEntityManager()) {
            entityManager
                    .find(Address.class, 1L)
                    .getOccupants()
                    .forEach(o->log.info("{}",o));
        } catch (Exception e) {
            log.error("Failed to execute query", e);
        }


    }
}
