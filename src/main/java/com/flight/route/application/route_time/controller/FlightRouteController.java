package com.flight.route.application.route_time.controller;


import com.flight.route.application.city.domain.City;
import com.flight.route.application.city.domain.CityPk;
import com.flight.route.application.city.service.CityService;
import com.flight.route.application.route_time.domain.FlightRoute;
import com.flight.route.application.route_time.domain.FlightRoutePk;
import com.flight.route.application.route_time.dto.FlightRouteDTO;
import com.flight.route.application.route_time.dto.FlightRoutePkDTO;
import com.flight.route.application.route_time.request.FlightRouteCreateDTO;
import com.flight.route.application.route_time.service.FlightRouteService;
import com.flight.route.application.route_time.service.request.FlightRouteCreateRequest;
import com.flight.route.application.utility.ApiPathConfigs;
import com.flight.route.application.utility.exception.ItemNotFoundException;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Vishal Ghanwat
 * @version 1.0.0
 */
@RestController
@DependsOn({"flightRouteService", "cityService"})
@RequestMapping(value = ApiPathConfigs.FLIGHT_ROUTE)
public class FlightRouteController {

    private static final Logger logger = LoggerFactory.getLogger(FlightRouteController.class);

    @Autowired
    private FlightRouteService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CityService cityService;

    @PostConstruct
    protected void onPostConstruct() {
        logger.info("onPostConstruct...");
        this.initializeTypeMaps();
    }

    @GetMapping(value = "from/{origin}")
    @ResponseBody
    public Iterable<FlightRouteDTO> findAllByOrigin(@PathVariable(value = "origin") String origin)
            throws IllegalArgumentException, ItemNotFoundException {
        City city = this.cityService.queryByCode(origin);
        return Optional.ofNullable(this.service.findAllByOrigin(city))
                .map(items -> StreamSupport.stream(items.spliterator(), false)
                        .filter(Objects::nonNull)
                        .map(item -> this.toDTO(item))
                        .collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
    }

    @GetMapping(value = "from/{origin}/to/{destiny}")
    @ResponseBody
    public FlightRouteDTO read(@PathVariable(value = "origin") String origin,
                               @PathVariable(value = "destiny") String destiny) throws IllegalArgumentException,
            ItemNotFoundException {
        City originCity = this.cityService.queryByCode(origin);
        City destinyCity = this.cityService.queryByCode(destiny);
        FlightRoutePk key = new FlightRoutePk(originCity, destinyCity);
        FlightRoute flightRoute = this.service.query(key);
        return this.toDTO(flightRoute);
    }

    @PostMapping(value = "from/{origin}/to/{destiny}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public FlightRouteDTO create(@PathVariable(value = "origin") String origin,
                                 @PathVariable(value = "destiny") String destiny,
                                 @RequestBody @Valid FlightRouteCreateDTO requestBody) throws IllegalArgumentException {
        City originCity = this.cityService.queryByCode(origin);
        City destinyCity = this.cityService.queryByCode(destiny);
        FlightRoute flightRoute = this.service.create(FlightRouteCreateRequest.builder()
                .origin(originCity)
                .destiny(destinyCity)
                .arrival(LocalDateTime.parse(requestBody.getArrival()))
                .departure(LocalDateTime.parse(requestBody.getDeparture()))
                .build());
        return this.toDTO(flightRoute);
    }

    @DeleteMapping(value = "from/{origin}/to/{destiny}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable(value = "origin") String origin,
                                 @PathVariable(value = "destiny") String destiny) throws IllegalArgumentException,
            ItemNotFoundException {
        City originCity = this.cityService.queryByCode(origin);
        City destinyCity = this.cityService.queryByCode(destiny);
        FlightRoutePk key = new FlightRoutePk(originCity, destinyCity);
        this.service.delete(key);
        return ResponseEntity.ok(null);
    }

    //region DTO mappings
    //--------------------------------------------------------------------------------

    private TypeMap<FlightRoute, FlightRouteDTO> typeMapDTO;

    private void initializeTypeMaps() {
        logger.info("executing initializeTypeMaps... ");
        Converter<FlightRoutePk, FlightRoutePkDTO> converterFeatureKey = ctx -> {
            FlightRoutePk key = ctx.getSource();
            if (Objects.isNull(key))
                return null;
            return FlightRoutePkDTO.builder()
                    .origin(Optional.ofNullable(key.getOrigin()).map(City::getKey).map(CityPk::getCode).orElse(null))
                    .originName(Optional.ofNullable(key.getOrigin()).map(City::getName).orElse(null))
                    .destiny(Optional.ofNullable(key.getDestiny()).map(City::getKey).map(CityPk::getCode).orElse(null))
                    .destinyName(Optional.ofNullable(key.getDestiny()).map(City::getName).orElse(null))
                    .build();
        };
        Converter<LocalDateTime, Long> converterTimestamp = ctx -> Objects.isNull(ctx.getSource()) ?
                null :
                ctx.getSource().toInstant(ZoneOffset.UTC).toEpochMilli();
        this.typeMapDTO = this.modelMapper.createTypeMap(FlightRoute.class, FlightRouteDTO.class)
                .addMappings(mapper -> {
                    mapper.using(converterFeatureKey).map(FlightRoute::getKey, FlightRouteDTO::setKey);
                    mapper.using(converterTimestamp).map(FlightRoute::getArrival, FlightRouteDTO::setArrival);
                    mapper.using(converterTimestamp).map(FlightRoute::getDeparture,
                            FlightRouteDTO::setDeparture);
                });
    }

    private FlightRouteDTO toDTO(FlightRoute flightRoute) {
        if (Objects.isNull(flightRoute))
            return null;
        return this.typeMapDTO.map(flightRoute);
    }
    //end of region DTO mappings
    //--------------------------------------------------------------------------------
}
