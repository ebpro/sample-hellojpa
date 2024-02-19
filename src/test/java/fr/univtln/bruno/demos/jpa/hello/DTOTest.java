package fr.univtln.bruno.demos.jpa.hello;

import fr.univtln.bruno.demos.jpa.hello.samples.ex_dto.CustomerDisplayDTO;
import fr.univtln.bruno.demos.jpa.hello.samples.ex_entity.CustomerGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;


 class DTOTest {
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
     void testCustomerDisplayDTOQuery() {
        CustomerGenerator.generateCustomer(10);
        TypedQuery<CustomerDisplayDTO> query = entityManager.createQuery("""
                SELECT new fr.univtln.bruno.demos.jpa.hello.samples.ex_dto.CustomerDisplayDTO(c.firstname, c.lastname)
                FROM Customer c""",
                CustomerDisplayDTO.class);

        var results = query.setMaxResults(5).getResultList();

        // Assert that the query returns results
        assertFalse(results.isEmpty());
    }
}