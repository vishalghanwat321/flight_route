package com.flight.route.application.city.service;


import com.flight.route.application.city.domain.City;
import com.flight.route.application.city.domain.CityPk;
import com.flight.route.application.city.repository.CityRepository;
import com.flight.route.application.utility.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Vishal Ghanwat
 * @version 1.0.0
 */
@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    /**
     * @param code
     * @return
     * @throws IllegalArgumentException
     * @throws ItemNotFoundException
     */
    @Transactional(readOnly = true)
    public City queryByCode(String code) throws IllegalArgumentException, ItemNotFoundException {
        if (StringUtils.isEmpty(code))
            throw new IllegalArgumentException("Failed to query city (reason: invalid code).");
        City city = this.repository.findByKey_Code(code);
        if (Objects.isNull(city))
            throw new ItemNotFoundException("City not found.");
        return city;
    }

    /**
     * @param key
     * @return
     * @throws IllegalArgumentException
     * @throws ItemNotFoundException
     */
    @Transactional(readOnly = true)
    public City query(CityPk key) throws IllegalArgumentException, ItemNotFoundException {
        if (Objects.isNull(key))
            throw new IllegalArgumentException("Failed to query city (reason: invalid key).");
        return Optional.ofNullable(this.repository.findById(key)).get()
                .orElseThrow(() -> new ItemNotFoundException("City not found."));
    }

    // TODO create, delete and update service will be implemented as per future needs
}
