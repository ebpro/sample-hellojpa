package fr.univtln.bruno.demos.jpa.hello;

import fr.univtln.bruno.demos.jpa.hello.samples.ex_ids.ex_embedded.Employee;
import fr.univtln.bruno.demos.jpa.hello.samples.ex_ids.ex_embedded.EmployeePK;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


 class EmbeddedIdTest {
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
    void persistEmployeeWithValidData() {
        Employee employee = new Employee();
        EmployeePK employeePK = new EmployeePK("Sales", 1);
        employee.setId(employeePK);
        employee.setName("John Doe");

        entityManager.persist(employee);

        entityManager.flush();
        entityManager.clear();

        Employee fetchedEmployee = entityManager.find(Employee.class, employeePK);

        assertNotNull(fetchedEmployee);
        assertEquals("John Doe", fetchedEmployee.getName());
    }
    @Test
     void persistEmployeeWithDuplicateIdThrowsException() {
        Employee employee1 = new Employee();
        EmployeePK employeePK1 = new EmployeePK("Sales", 1);
        employee1.setId(employeePK1);
        employee1.setName("John Doe");

        Employee employee2 = new Employee();
        EmployeePK employeePK2 = new EmployeePK("Sales", 1);
        employee2.setId(employeePK2);
        employee2.setName("Jane Doe");

        entityManager.persist(employee1);
        assertThrows( jakarta.persistence.PersistenceException.class,
                ()->entityManager.persist(employee2));

        entityManager.flush();
    }
}