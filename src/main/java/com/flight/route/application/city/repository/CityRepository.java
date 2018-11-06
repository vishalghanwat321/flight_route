package com.flight.route.application.city.repository;


import com.flight.route.application.city.domain.City;
import com.flight.route.application.city.domain.CityPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author Vishal Ghanwat
 * @version 1.0.0
 */
@Repository
public interface CityRepository extends JpaRepository<City, CityPk>, JpaSpecificationExecutor, Serializable {

    City findByKey_Code(String code);
}
