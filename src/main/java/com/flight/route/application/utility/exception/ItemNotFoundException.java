package com.flight.route.application.utility.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Vishal Ghanwat
 * @version 1.0.0
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends RuntimeException {

    /**
     * @param errorMessage
     */
    public ItemNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}