package com.flight.route.application.route_time.domain;


import lombok.*;

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
public class FlightRoute implements Serializable {

    @EmbeddedId
    @NonNull
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private FlightRoutePk key;

    @Column(name = "arrival_date_time", columnDefinition = "TIMESTAMP", nullable = false)
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private LocalDateTime arrival;

    @Column(name = "departure_date_time", columnDefinition = "TIMESTAMP", nullable = false)
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private LocalDateTime departure;

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

    @PreUpdate
    protected void onPreUpdate() {
        this.lastModificationTimestamp = LocalDateTime.now();
    }

    /**
     * @param key
     * @param departure
     * @param arrival
     * @throws IllegalArgumentException
     */
    public FlightRoute(FlightRoutePk key, LocalDateTime departure, LocalDateTime arrival)
            throws IllegalArgumentException {
        if (Objects.isNull(key))
            throw new IllegalArgumentException("Failed to instantiate flight-route (reason: invalid key.)");
        if (Objects.isNull(departure))
            throw new IllegalArgumentException("Failed to instantiate flight-route (reason: invalid departure date time.)");
        if (Objects.isNull(arrival))
            throw new IllegalArgumentException("Failed to instantiate flight-route (reason: invalid arrival date time.)");
        this.key = key;
        this.departure = departure;
        this.arrival = arrival;
    }
}