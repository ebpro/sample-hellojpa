package fr.univtln.bruno.demos.jpa.hello.samples.ex_heritage1;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)

@Entity
public class Teacher extends Person {
    private double salary;
}
