package fr.univtln.bruno.demos.jpa.hello.samples.ex_heritage1;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter

@Entity
public class Student extends Person {
    public enum Status {ACTIVE, ALUMNI}

    private Status status;
}
