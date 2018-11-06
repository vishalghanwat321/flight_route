package com.flight.route.application.route_time.service.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.flight.route.application.city.domain.City;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Vishal Ghanwat
 * @version 1.0.0
 */
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightRouteCreateRequest {

    @Getter
    @Setter
    @JsonProperty("origin")
    private City origin;

    @Getter
    @Setter
    @JsonProperty("destiny")
    private City destiny;

    @Getter
    @Setter
    @JsonProperty("arrival")
    private LocalDateTime arrival;

    @Getter
    @Setter
    @JsonProperty("departure")
    private LocalDateTime departure;
}