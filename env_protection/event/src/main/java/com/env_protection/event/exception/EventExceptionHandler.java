package com.env_protection.event.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EventExceptionHandler {

    @ExceptionHandler(EventNotFoundException.class)
    private ResponseEntity<?> handleAppUserNotFoundException(EventNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Event [" + exception.getEventId() + "] nowhere to be found");
    }

    @ExceptionHandler(EventTypeNotFoundException.class)
    private ResponseEntity<?> handleAppUserDuplicatedException(EventTypeNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Event type [" + exception.getTypeId() + "] nowhere to be found");
    }
}
