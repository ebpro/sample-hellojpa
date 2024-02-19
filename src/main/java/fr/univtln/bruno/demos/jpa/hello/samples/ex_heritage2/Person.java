package fr.univtln.bruno.demos.jpa.hello.samples.ex_heritage2;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Table(name="PERSON", schema = "EX_HERITAGE2")
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString

@Entity(name="PersonHeritage2")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue
    long id;

    private String name;
}
