package fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_onetomany_a;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "LINE", schema = "EX_ONE_TO_MANY_A")
@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @NonNull
    private String product;
    @NonNull
    private double price;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Order order;

    public void setOrder(Order order) {
        if (this.order != null) {
            this.order.getLines().remove(this);
        }
        this.order = order;
        if (order != null) {
            order.getLines().add(this);
        }
    }
}
