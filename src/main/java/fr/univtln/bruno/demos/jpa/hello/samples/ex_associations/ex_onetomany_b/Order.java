package fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_onetomany_b;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "EORDER", schema = "EX_ONE_TO_MANY_B")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Builder.Default
    private LocalDateTime date = LocalDateTime.now();

    @ElementCollection
    @Singular
    private List<Line> lines = new ArrayList<>();
}
