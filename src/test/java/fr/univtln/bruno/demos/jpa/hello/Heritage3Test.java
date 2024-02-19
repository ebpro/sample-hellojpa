package fr.univtln.bruno.demos.jpa.hello;

import fr.univtln.bruno.demos.jpa.hello.samples.ex_heritage3.Student;
import fr.univtln.bruno.demos.jpa.hello.samples.ex_heritage3.Teacher;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
 class Heritage3Test {
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
     void testPersistStudentAndTeacher() {
        Student student = Student.builder().name("Pierre").status(Student.Status.ACTIVE).build();
        Teacher teacher = Teacher.builder().name("Marie").salary(3000).build();

        entityManager.persist(student);
        entityManager.persist(teacher);

        // Flush and clear the persistence context to force a database round trip
        entityManager.flush();
        entityManager.clear();

        // Fetch the persisted student and teacher from the database
        Student fetchedStudent = entityManager.find(Student.class, student.getId());
        Teacher fetchedTeacher = entityManager.find(Teacher.class, teacher.getId());

        // Assert that the fetched student and teacher are not null
        assertNotNull(fetchedStudent);
        assertNotNull(fetchedTeacher);
    }
}