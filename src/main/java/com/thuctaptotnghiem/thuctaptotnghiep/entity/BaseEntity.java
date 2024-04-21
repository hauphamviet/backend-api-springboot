package com.thuctaptotnghiem.thuctaptotnghiep.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Builder.Default
    @Column(name = "created_date")
    @JsonIgnore
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedDate
    @Builder.Default
    @Column(name = "modified_date")
    @JsonIgnore
    private LocalDateTime modifiedDate = LocalDateTime.now();

    @Builder.Default
    @JsonIgnore
    @Version
    private int version = -1;


}
