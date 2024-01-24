package fr.univtln.bruno.demos.jpa.hello.samples.ex_simple;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@ToString
@Getter
public class Customer {
    @Id
    @GeneratedValue
    private long id;
    @NonNull
    private String email;

    private String name;

}
