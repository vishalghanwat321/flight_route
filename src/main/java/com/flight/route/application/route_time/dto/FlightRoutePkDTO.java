package com.flight.route.application.route_time.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vishal Ghanwat
 * @version 1.0.0
 */
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightRoutePkDTO {

    @Getter
    @Setter
    @JsonProperty("originCityCode")
    private String origin;

    @Getter
    @Setter
    @JsonProperty("originCityName")
    private String originName;

    @Getter
    @Setter
    @JsonProperty("destinyCityCode")
    private String destiny;

    @Getter
    @Setter
    @JsonProperty("destinyCityName")
    private String destinyName;
}
