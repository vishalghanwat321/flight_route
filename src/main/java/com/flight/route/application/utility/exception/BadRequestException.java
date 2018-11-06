package com.flight.route.application.utility.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Vishal Ghanwat
 * @version 1.0.0
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    /**
     * @param errorMessage
     */
    public BadRequestException(String errorMessage) {
        super(errorMessage);
    }
}