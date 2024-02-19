package fr.univtln.bruno.demos.jpa.hello.samples.ex_ids.ex_embedded;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name="EmployeEmbeddedId")
@Table(name="EMPLOYE", schema = "EX_EMBEDDED_ID")
public class Employee {
    @EmbeddedId
    private EmployeePK id;
    private String name;
}
