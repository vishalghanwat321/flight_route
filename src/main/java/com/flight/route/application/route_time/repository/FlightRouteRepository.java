package com.flight.route.application.route_time.repository;


import com.flight.route.application.city.domain.City;
import com.flight.route.application.route_time.domain.FlightRoute;
import com.flight.route.application.route_time.domain.FlightRoutePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author Vishal Ghanwat
 * @version 1.0.0
 */
@Repository
public interface FlightRouteRepository extends JpaRepository<FlightRoute, FlightRoutePk>,
        JpaSpecificationExecutor, Serializable {

    Iterable<FlightRoute> findAllByKey_Origin(City origin);  // method to get all routes from origin
}