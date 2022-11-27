package com.env_protection.event.exception;

/**
 * com.env_protection.event.exception
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 2:20 PM
 * Description: ...
 */
public class EventNotFoundException extends EventException {
    public EventNotFoundException(String eventId) {
        super(eventId);
    }
}
