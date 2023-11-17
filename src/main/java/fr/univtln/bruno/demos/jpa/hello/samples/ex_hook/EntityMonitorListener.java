package fr.univtln.bruno.demos.jpa.hello.samples.ex_hook;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntityMonitorListener {

    @PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(Object entity) {
        log.info("-->about to change in DB: " + entity);
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Object entity) {
        log.info("--> changed in DB" + entity);
    }

    @PostLoad
    private void afterLoad(Object entity) {
        log.info("--> loaded from DB: " + entity);
    }
}
