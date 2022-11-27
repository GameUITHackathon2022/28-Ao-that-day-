package com.linkho.authentication.exception;

public class AppUserDuplicatedException extends AppUserException {
    public AppUserDuplicatedException(String username) {
        super(username);
    }
}
