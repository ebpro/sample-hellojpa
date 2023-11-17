package fr.univtln.bruno.demos.jpa.hello.samples.ex_biography;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CUSTOMER", schema = "ex_biography")
public class Customer {
    @Id
    @GeneratedValue
    private long id;

    @Column(length = 50, nullable = false)
    @NonNull
    private String name;

    @Embedded
    private Biography biography;
}
