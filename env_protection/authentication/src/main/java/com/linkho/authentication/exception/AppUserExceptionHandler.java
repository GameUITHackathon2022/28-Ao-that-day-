package com.linkho.authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppUserExceptionHandler {


    @ExceptionHandler(AppUserNotFoundException.class)
    private ResponseEntity<?> handleAppUserNotFoundException(AppUserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("User [" + exception.getUsername() + "] nowhere to be found");
    }

    @ExceptionHandler(AppUserDuplicatedException.class)
    private ResponseEntity<?> handleAppUserDuplicatedException(AppUserDuplicatedException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body("User [" + exception.getUsername() + "] already exists");
    }

    @ExceptionHandler(InvalidUsernameOrPasswordException.class)
    private ResponseEntity<?> handleInvalidUsernameOrPasswordException(InvalidUsernameOrPasswordException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Username or password is invalid");
    }


    @ExceptionHandler(RefreshTokenMissingException.class)
    private ResponseEntity<?> handleRefreshTokenMissingException(RefreshTokenMissingException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body("Refresh token is missing");
    }

    @ExceptionHandler(TokenExpiredException.class)
    private ResponseEntity<?> handleTokenExpiredException(TokenExpiredException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body("The provided token is expired");
    }
}
