package fr.univtln.bruno.demos.jpa.hello.samples.ex_entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "CUSTOMER",
       indexes = {@Index(name = "idx_email", columnList = "email", unique = true)})
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {
    @Column(updatable = false)
    private final LocalDateTime creationDate = LocalDateTime.now();
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private long id;

    @Column(length = 50, nullable = false, unique = true)
    @NonNull
    private String email;

    @Column(length = 50, nullable = false)
    private String firstname;

    @Column(length = 50, nullable = false)
    private String lastname;

    @Transient
    private String displayName;

    @Setter
    private LocalDate birthDate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @ToString.Exclude
    private byte[] photo;
    //@Enumerated(EnumType.STRING)
    private Status status = Status.LEAD;

    @Version
    protected Integer version;

    enum Status {ACTIVE, LEAD}
}