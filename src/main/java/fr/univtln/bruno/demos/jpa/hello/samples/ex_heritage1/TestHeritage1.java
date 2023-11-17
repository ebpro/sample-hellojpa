package fr.univtln.bruno.demos.jpa.hello.samples.ex_heritage1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestHeritage1 {
    public static void main(String[] args) {
        Student student = Student.builder().name("Pierre").status(Student.Status.ACTIVE).build();
        Teacher teacher = Teacher.builder().name("Marie").salary(3000).build();

        log.info("{}",student);
        log.info("{}",teacher);

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa-pu")) {
            try (EntityManager entityManager = emf.createEntityManager()) {

                entityManager.getTransaction().begin();
                entityManager.persist(student);
                entityManager.persist(teacher);
                entityManager.getTransaction().commit();
            }
        }
    }
}
