package fr.univtln.bruno.demos.jpa.hello.samples.ex_entity;

import fr.univtln.bruno.demos.jpa.hello.DatabaseManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import net.datafaker.Faker;

import java.util.Random;
import java.util.stream.Stream;

public class CustomerGenerator {

    private static Random random = new Random();

    private CustomerGenerator() {}

    public static void generateCustomer(int number) {
        try (EntityManager entityManager = DatabaseManager.getInstance().getEntityManagerFactory().createEntityManager()) {

            TypedQuery<Boolean> checkEmail = entityManager
                    .createQuery("""
                            SELECT COUNT(*) > 0  
                            FROM Customer c 
                            WHERE c.email = :email""", Boolean.class);

            Faker faker = new Faker();

            entityManager.getTransaction().begin();
            Stream.generate(() -> {
                        String firstname = faker.name().firstName();
                        String lastname = faker.name().lastName();
                        String provider = faker.internet().domainName();
                        String email = "%s.%s@%s".formatted(firstname, lastname, provider);
                        while (Boolean.TRUE.equals(checkEmail.setParameter("email", email).getSingleResult()))
                            email = "%s.%s-%s@%s".formatted(firstname, lastname, random.nextInt(1000) + 1, provider);
                        return Customer.builder()
                                .email(email)
                                .firstname(firstname)
                                .lastname(lastname).build();
                    })
                    .limit(number)
                    .forEach(entityManager::persist);

            entityManager.getTransaction().commit();
        }
    }
}
