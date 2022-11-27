package com.env_protection.event.exception;

public class EventTypeNotFoundException extends EventTypeException {
    public EventTypeNotFoundException(String typeId) {
        super(typeId);
    }
}
