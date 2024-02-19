package fr.univtln.bruno.demos.jpa.hello;

import fr.univtln.bruno.demos.jpa.hello.samples.ex_ids.ex_idclass.Employee;
import fr.univtln.bruno.demos.jpa.hello.samples.ex_ids.ex_idclass.EmployeePK;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

 class IdClassTest {
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
        employee.setDepartment("Sales");
        employee.setRankInDepartment(1);
        employee.setName("John Doe");

        entityManager.persist(employee);

        // Flush and clear the persistence context to force a database round trip
        entityManager.flush();
        entityManager.clear();

        // Fetch the persisted employee from the database
        EmployeePK employeePK = new EmployeePK("Sales", 1);
        Employee fetchedEmployee = entityManager.find(Employee.class, employeePK);

        // Assert that the fetched employee is not null
        assertNotNull(fetchedEmployee);
        // Assert that the fetched employee's name is as expected
        assertEquals("John Doe", fetchedEmployee.getName());
    }

    @Test
     void persistEmployeeWithDuplicateIdThrowsException() {
        Employee employee1 = new Employee();
        employee1.setDepartment("Sales");
        employee1.setRankInDepartment(1);
        employee1.setName("John Doe");

        Employee employee2 = new Employee();
        employee2.setDepartment("Sales");
        employee2.setRankInDepartment(1);
        employee2.setName("Jane Doe");

        entityManager.persist(employee1);
        assertThrows( jakarta.persistence.PersistenceException.class,
                ()->entityManager.persist(employee2));

        // Flush and clear the persistence context to force a database round trip
        entityManager.flush();
    }
}