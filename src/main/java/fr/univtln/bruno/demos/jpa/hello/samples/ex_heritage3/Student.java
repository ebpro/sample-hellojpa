package fr.univtln.bruno.demos.jpa.hello.samples.ex_heritage3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString(callSuper = true)

@Entity(name="StudentHeritage3")
@Table(name="STUDENT", schema = "EX_HERITAGE3")
public class Student extends Person {
    public enum Status {ACTIVE, ALUMNI}

    @Column(nullable = false)
    private Status status;
}
