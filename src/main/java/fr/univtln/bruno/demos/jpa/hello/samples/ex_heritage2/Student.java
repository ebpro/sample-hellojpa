package fr.univtln.bruno.demos.jpa.hello.samples.ex_heritage2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity(name="StudentHeritage2")
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@ToString(callSuper = true)
@Table(name="STUDENT", schema = "EX_HERITAGE2")
public class Student extends Person {
    enum Status {ACTIVE, ALUMNI}

    @Column(nullable = false)
    private Status status;
}
