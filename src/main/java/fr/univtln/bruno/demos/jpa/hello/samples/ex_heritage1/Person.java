package fr.univtln.bruno.demos.jpa.hello.samples.ex_heritage1;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString

@Entity
@Table(schema = "EX_HERITAGE1")
public class Person {
    @Id
    @GeneratedValue
    long id;

    private String name;
}
