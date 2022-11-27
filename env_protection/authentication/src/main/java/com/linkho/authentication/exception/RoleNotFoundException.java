package com.linkho.authentication.exception;

public class RoleNotFoundException extends RoleException {

    public RoleNotFoundException(String username) {
        super(username);
    }
}
