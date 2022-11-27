package com.env_protection.event.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * com.env_protection.event.exception
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 2:17 PM
 * Description: ...
 */
@AllArgsConstructor
@Data
public class EventException extends Exception {
    private String eventId;
}