package fr.univtln.bruno.demos.jpa.hello.samples.ex_hook;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "CUSTOMER", schema = "EX_HOOK")
@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class Customer {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String name;

    @PrePersist
    public void logPrePersist() {
        log.info("About to persist: " + this);
    }

    @PostPersist
    public void logPostPersist() {
        log.info("Persisted: " + this);
    }

    @PreUpdate
    public void logPPreUpdate() {
        log.info("About to update: " + this);
    }

    @PostUpdate
    public void logPostUpdate() {
        log.info("Updated: " + this);
    }

    @PreRemove
    public void logPPreRemove() {
        log.info("About to remove: " + this);
    }

    @PostRemove
    public void logPostRemove() {
        log.info("Removed: " + this);
    }

    @PostLoad
    public void logPostLoad() {
        log.info("Loaded: " + this);
    }

}
