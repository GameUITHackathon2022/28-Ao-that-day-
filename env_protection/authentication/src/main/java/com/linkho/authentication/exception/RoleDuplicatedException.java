package com.linkho.authentication.exception;

public class RoleDuplicatedException extends RoleException {
    public RoleDuplicatedException(String username) {
        super(username);
    }
}
