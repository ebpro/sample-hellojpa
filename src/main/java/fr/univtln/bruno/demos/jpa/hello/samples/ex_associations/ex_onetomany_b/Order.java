package fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_onetomany_b;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor

@Entity
@Table(name = "EORDER", schema = "EX_ONE_TO_MANY_B")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @ElementCollection
    @CollectionTable(name = "LINE", schema = "EX_ONE_TO_MANY_B")
    @Singular
    private Set<Line> lines = new HashSet<>();

}
