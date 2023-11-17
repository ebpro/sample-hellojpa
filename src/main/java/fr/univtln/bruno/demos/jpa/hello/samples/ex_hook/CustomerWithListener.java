package fr.univtln.bruno.demos.jpa.hello.samples.ex_hook;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.java.Log;

@Entity
@Data
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CUSTOMER_BIS")
@Log
@EntityListeners(EntityMonitorListener.class)
public class CustomerWithListener {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String name;
}
