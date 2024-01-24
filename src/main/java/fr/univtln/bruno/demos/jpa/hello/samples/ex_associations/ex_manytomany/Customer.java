package fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_manytomany;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Address> places = new HashSet<>();

    public void addPlace(Address address) {
        this.places.add(address);
        address.getOccupants().add(this);
    }

    public void removePlace(Address address) {
        this.places.remove(address);
        address.getOccupants().remove(this);
    }
}
