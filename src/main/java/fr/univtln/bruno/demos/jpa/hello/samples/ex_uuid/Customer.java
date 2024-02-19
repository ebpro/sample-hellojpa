package fr.univtln.bruno.demos.jpa.hello.samples.ex_uuid;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name = "CUSTOMER", schema = "EX_UUID")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(length = 50, nullable = false)
    @NonNull
    private String name;
}
