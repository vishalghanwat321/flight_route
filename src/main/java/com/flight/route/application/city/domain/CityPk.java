package com.flight.route.application.city.domain;


import com.flight.route.application.country.domain.Country;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Vishal Ghanwat
 * @version 1.0.0
 */
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class CityPk implements Serializable {

    @Column(name = "code", columnDefinition = "TEXT", updatable = false)
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private String code;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "country_code", nullable = false, updatable = false)
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private Country country;
}