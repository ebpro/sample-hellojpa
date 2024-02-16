package fr.univtln.bruno.demos.jpa.hello.samples.ex_simple;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity                             // <1>
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@ToString
@Getter
public class Customer {
    @Id                                 //<2>
    @GeneratedValue         //<2>
    private long id;

    @NonNull                       // <3>
    private String email;

    private String name;

}
