package com.flight.route.application.route_time.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vishal Ghanwat
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightRouteDTO {

    @Getter
    @Setter
    private FlightRoutePkDTO key;

    @Getter
    @Setter
    @JsonProperty("departure")
    private Long departure;

    @Getter
    @Setter
    @JsonProperty("arrival")
    private Long arrival;
}
