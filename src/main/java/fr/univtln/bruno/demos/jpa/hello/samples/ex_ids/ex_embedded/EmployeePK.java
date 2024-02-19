package fr.univtln.bruno.demos.jpa.hello.samples.ex_ids.ex_embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePK implements Serializable {
    private String department;
    private int rankInDepartment;
}
