package com.cgi.assignment.recipe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LogsNotFoundException extends RuntimeException {

    public LogsNotFoundException(String message) {
        super(message);
    }
}
