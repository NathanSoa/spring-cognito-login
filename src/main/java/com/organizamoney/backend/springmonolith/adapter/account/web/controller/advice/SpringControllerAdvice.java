package com.organizamoney.backend.springmonolith.adapter.account.web.controller.advice;

import com.organizamoney.backend.springmonolith.adapter.shared.web.controller.HttpError;
import com.organizamoney.backend.springmonolith.domain.account.application.exceptions.WrongCredentialsException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class SpringControllerAdvice {

    public static Logger LOGGER = LoggerFactory.getLogger(SpringControllerAdvice.class);

    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<HttpError> handleWrongCredentialsException(WrongCredentialsException e, WebRequest request) {
        LOGGER.error(e.getMessage());
        final var error = new HttpError();
        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        error.setRoute(request.getDescription(false));
        return ResponseEntity.status(error.getCode()).body(error);
    }
}
