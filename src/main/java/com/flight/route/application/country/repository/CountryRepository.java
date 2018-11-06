package com.flight.route.application.country.repository;


import com.flight.route.application.country.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author Vishal Ghanwat
 * @version 1.0.0
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String>, JpaSpecificationExecutor, Serializable {
}
