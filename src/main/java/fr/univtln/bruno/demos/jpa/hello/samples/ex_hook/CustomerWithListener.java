package fr.univtln.bruno.demos.jpa.hello.samples.ex_hook;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "CUSTOMER_BIS")
@EntityListeners(EntityMonitorListener.class)
@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class CustomerWithListener {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String name;
}
