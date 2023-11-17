package fr.univtln.bruno.demos.jpa.hello.samples;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CUSTOMER_BASE")
public class CustomerBase {
    @Id
    @GeneratedValue
    @NonNull
    private long id;

    @Column(length = 50, nullable = false)
    @NonNull
    private String name;
}
