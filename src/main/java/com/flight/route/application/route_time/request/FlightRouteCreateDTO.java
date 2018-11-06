package com.flight.route.application.route_time.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Vishal Ghanwat
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightRouteCreateDTO {

    @NonNull
    @NotNull
    @Getter
    @Setter
    @JsonProperty(value = "arrival", required = true)
    private String arrival;

    @NonNull
    @NotNull
    @Getter
    @Setter
    @JsonProperty(value = "departure", required = true)
    private String departure;
}
