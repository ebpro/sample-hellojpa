package fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_onetomany_a;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EORDER", schema = "EX_ONE_TO_MANY_A")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private LocalDateTime date = LocalDateTime.now();

    @OneToMany(mappedBy = "order",
            cascade = {CascadeType.PERSIST,
                    CascadeType.REMOVE},
            orphanRemoval = true)
    @ToString.Exclude
    private Set<Line> lines = new HashSet<>();

    public Order addLine(Line line) {
        line.setOrder(this);
        lines.add(line);
        return this;
    }

    public Order removeLine(Line line) {
        line.setOrder(this);
        lines.remove(line);
        return this;
    }
}
