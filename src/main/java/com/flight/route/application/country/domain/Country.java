package com.flight.route.application.country.domain;


import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Vishal Ghanwat
 * @version 1.0.0
 */

@Entity
@Table
@Cacheable(false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Country implements Serializable {

    @Id
    @Column(name = "code", columnDefinition = "TEXT", updatable = false)
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private String code;

    @Column(name = "name", columnDefinition = "TEXT", nullable = false)
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private String name;

    @Column(name = "creation_timestamp", columnDefinition = "TIMESTAMP", updatable = false, nullable = false)
    @Getter
    protected LocalDateTime creationTimestamp;

    @Column(name = "last_modification_timestamp", columnDefinition = "TIMESTAMP")
    @Getter
    protected LocalDateTime lastModificationTimestamp;

    @PrePersist
    protected void onPrePersist() {
        this.creationTimestamp = LocalDateTime.now();
    }

    /**
     * @param code
     * @param name
     * @throws IllegalArgumentException
     */
    public Country(String code, String name) throws IllegalArgumentException {
        if (StringUtils.isEmpty(code))
            throw new IllegalArgumentException("Failed to instantiate city (reason: invalid code.)");
        if (StringUtils.isEmpty(name))
            throw new IllegalArgumentException("Failed to instantiate city (reason: invalid name.)");
        this.code = code;
        this.name = name;
    }
}
