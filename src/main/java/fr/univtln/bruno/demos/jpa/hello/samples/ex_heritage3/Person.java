package fr.univtln.bruno.demos.jpa.hello.samples.ex_heritage3;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Table(name="PERSON", schema = "EX_HERITAGE3")
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString

@Entity(name="PersonHeritage3")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
    @Id
    @GeneratedValue
    long id;

    private String name;
}
