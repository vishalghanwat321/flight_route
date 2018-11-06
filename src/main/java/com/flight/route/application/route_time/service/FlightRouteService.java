package com.flight.route.application.route_time.service;


import com.flight.route.application.city.domain.City;
import com.flight.route.application.route_time.domain.FlightRoute;
import com.flight.route.application.route_time.domain.FlightRoutePk;
import com.flight.route.application.route_time.repository.FlightRouteRepository;
import com.flight.route.application.route_time.service.request.FlightRouteCreateRequest;
import com.flight.route.application.utility.exception.BadRequestException;
import com.flight.route.application.utility.exception.ItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Vishal Ghanwat
 * @version 1.0.0
 */
@Service
public class FlightRouteService {

    private static final Logger logger = LoggerFactory.getLogger(FlightRouteService.class);

    @Autowired
    private FlightRouteRepository repository;

    @Transactional(readOnly = true)
    public Iterable<FlightRoute> findAll(Specification<FlightRoute> specification) {
        // TODO filter records based on departure date time greater than current date time
        return this.repository.findAll(specification);
    }

    @Transactional(readOnly = true)
    public Iterable<FlightRoute> findAllByOrigin(City origin) {
        // TODO filter records based on departure date time greater than current date time
        return this.repository.findAllByKey_Origin(origin);
    }

    /**
     * @param key
     * @return
     * @throws IllegalArgumentException
     * @throws ItemNotFoundException
     */
    @Transactional(readOnly = true)
    public FlightRoute query(FlightRoutePk key) throws IllegalArgumentException, ItemNotFoundException {
        if (Objects.isNull(key))
            throw new IllegalArgumentException("Failed to query flight route (reason: invalid key).");
        return Optional.ofNullable(this.repository.findById(key)).get()
                .orElseThrow(() -> new ItemNotFoundException("Flight route not found."));
    }

    /**
     * @param request
     * @return
     * @throws IllegalArgumentException
     */
    @Transactional
    public FlightRoute create(FlightRouteCreateRequest request) throws IllegalArgumentException, BadRequestException {
        if (Objects.isNull(request))
            throw new IllegalArgumentException("Failed to query flight route (reason: invalid request).");
        if (Objects.isNull(request.getArrival()) || Objects.isNull(request.getDeparture()))
            throw new IllegalArgumentException("Failed to query flight route (reason: invalid arrival or departure" +
                    "date time).");
        if (request.getDeparture().isAfter(request.getArrival()))
            throw new BadRequestException("Departure date time can not be earlier than arrival date time.");
        FlightRoutePk key = new FlightRoutePk(request.getOrigin(), request.getDestiny());
        FlightRoute flightRoute = new FlightRoute(key, request.getDeparture(), request.getArrival());
        return this.repository.save(flightRoute);
    }

    // TODO update service will be implemented as per future needs

    /**
     * @param key
     * @throws IllegalArgumentException
     * @throws ItemNotFoundException
     */
    @Transactional
    public void delete(FlightRoutePk key) throws IllegalArgumentException, ItemNotFoundException {
        FlightRoute flightRoute = this.query(key);
        this.repository.delete(flightRoute);
    }
}