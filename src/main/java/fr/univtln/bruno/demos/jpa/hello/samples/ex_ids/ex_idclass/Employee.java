package fr.univtln.bruno.demos.jpa.hello.samples.ex_ids.ex_idclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity(name="EmployeIdClass")
@Table(name="EMPLOYE", schema = "EX_IDCLASS")
@IdClass(EmployePK.class)
public class Employee {
    @Id
    private String department;
    @Id
    private int rankInDepartment;
    private String name;
}
