package com.env_protection.event.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EventTypeException extends Exception {
    protected final String typeId;

}
