package com.parsley.infread.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@Getter
@MappedSuperclass
public abstract class AbstractAuditingEntity {

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamp")
    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
