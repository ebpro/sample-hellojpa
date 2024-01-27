package fr.univtln.bruno.demos.jpa.hello.samples.ex_ids.ex_idclass;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
public class EmployePK implements Serializable {
    @Id
    private String department;
    @Id
    private int rankInDepartment;
}
