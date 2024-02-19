package fr.univtln.bruno.demos.jpa.hello.samples.ex_heritage2;

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

@Entity(name="TeacherHeritage2")
@Table(name="TEACHER", schema = "EX_HERITAGE2")
public class Teacher extends Person {

    @Column(nullable = false)
    private double salary;
}
