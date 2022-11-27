package com.linkho.authentication.exception;

public class InvalidUsernameOrPasswordException extends AppUserException {
    public InvalidUsernameOrPasswordException(String username) {
        super(username);
    }
}
