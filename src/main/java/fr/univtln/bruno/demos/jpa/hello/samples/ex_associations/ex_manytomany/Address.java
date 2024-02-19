package fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_manytomany;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name = "ADDRESS", schema = "EX_MANY_TO_MANY")
public class Address {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String addressDetail;

    @ManyToMany(mappedBy = "places")
    @ToString.Exclude
    private Set<Customer> occupants= new HashSet<>();

    public void addOccupant(Customer customer) {
        this.occupants.add(customer);
        customer.getPlaces().add(this);
    }

    public void removeOccupant(Customer customer) {
        this.occupants.remove(customer);
        customer.getPlaces().remove(this);
    }

}
