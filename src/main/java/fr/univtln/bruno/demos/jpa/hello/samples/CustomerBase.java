package fr.univtln.bruno.demos.jpa.hello.samples;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name = "CUSTOMER_BASE")
public class CustomerBase {
    @Id
    @GeneratedValue
    private long id;

    @Column(length = 50, nullable = false)
    @NonNull
    private String name;
}
