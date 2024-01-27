package fr.univtln.bruno.demos.jpa.hello.samples.ex_ids.ex_embedded;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
public class EmployeePK implements Serializable {
    private String department;
    private int rankInDepartment;
}
