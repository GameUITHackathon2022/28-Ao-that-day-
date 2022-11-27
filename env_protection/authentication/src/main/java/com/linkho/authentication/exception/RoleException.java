package com.linkho.authentication.exception;
// RoleException.java

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class RoleException extends Exception{
    protected final String name;
}

