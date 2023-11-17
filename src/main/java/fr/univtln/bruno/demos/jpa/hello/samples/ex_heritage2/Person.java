package fr.univtln.bruno.demos.jpa.hello.samples.ex_heritage2;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name="PersonHeritage2")
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Table(name="PERSON", schema = "EX_HERITAGE2")
public class Person {
    @Id
    @GeneratedValue
    long id;

    private String name;
}
