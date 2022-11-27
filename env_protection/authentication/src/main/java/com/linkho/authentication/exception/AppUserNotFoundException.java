package com.linkho.authentication.exception;

public class AppUserNotFoundException extends AppUserException {
    public AppUserNotFoundException(String username) {
        super(username);
    }
}

