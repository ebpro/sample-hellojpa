package fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_manytomany;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMER", schema = "EX_MANY_TO_MANY")
@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String name;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(schema = "EX_MANY_TO_MANY")
    private List<Address> places = new ArrayList<>();
}
