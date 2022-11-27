package com.linkho.authentication.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * com.linkho.authentication.exceptions
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 1:14 AM
 * Description: ...
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class AppUserException extends Exception {
    protected final String username;
}
