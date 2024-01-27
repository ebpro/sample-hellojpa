package fr.univtln.bruno.demos.jpa.hello.samples.ex_ids.ex_embedded;

import jakarta.persistence.*;

@Entity(name="EmployeEmbeddedId")
@Table(name="EMPLOYE", schema = "EX_EMBEDDED_ID")
public class Employee {
    @EmbeddedId
    private EmployeePK id;
    private String name;
}
