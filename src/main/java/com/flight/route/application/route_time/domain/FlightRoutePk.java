package com.flight.route.application.route_time.domain;


import com.flight.route.application.city.domain.City;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * @author Vishal Ghanwat
 * @version 1.0.0
 */
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class FlightRoutePk implements Serializable {

    @OneToOne(cascade = CascadeType.REMOVE)
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private City origin;

    @OneToOne(cascade = CascadeType.REMOVE)
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private City destiny;
}