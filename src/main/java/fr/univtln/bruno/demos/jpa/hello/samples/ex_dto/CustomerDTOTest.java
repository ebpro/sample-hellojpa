package fr.univtln.bruno.demos.jpa.hello.samples.ex_dto;

import fr.univtln.bruno.demos.jpa.hello.DatabaseManager;
import fr.univtln.bruno.demos.jpa.hello.samples.ex_entity.CustomerGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerDTOTest {
    public static void main(String[] args) {

        try (EntityManager entityManager = DatabaseManager.getInstance().getEntityManagerFactory().createEntityManager()) {
            CustomerGenerator.generateCustomer(10);
            TypedQuery<CustomerDisplayDTO> query = entityManager.createQuery("""
                            SELECT new fr.univtln.bruno.demos.jpa.hello.samples.ex_dto.CustomerDisplayDTO(c.firstname, c.lastname)
                            FROM Customer c""",
                    CustomerDisplayDTO.class);

            query.setMaxResults(5).getResultList().stream()
                    .map(CustomerDisplayDTO::toString)
                    .forEach(log::info);

        }

    }
}
