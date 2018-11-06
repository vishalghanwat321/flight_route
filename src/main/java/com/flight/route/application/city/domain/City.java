package com.flight.route.application.city.domain;


import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Vishal Ghanwat
 * @version 1.0.0
 */
@Entity
@Table
@Cacheable(false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class City implements Serializable {

    @EmbeddedId
    @NonNull
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private CityPk key;

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
     * @param key
     * @param name
     * @throws IllegalArgumentException
     */
    public City(CityPk key, String name) throws IllegalArgumentException {
        if (Objects.isNull(key))
            throw new IllegalArgumentException("Failed to instantiate city (reason: invalid key.)");
        if (StringUtils.isEmpty(name))
            throw new IllegalArgumentException("Failed to instantiate city (reason: invalid name.)");
        this.key = key;
        this.name = name;
    }
}
