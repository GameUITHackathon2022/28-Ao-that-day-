package com.env_protection.event.service;

import com.env_protection.event.dto.request.AttendEventRequestDto;
import com.env_protection.event.dto.request.CreateEventRequestDto;
import com.env_protection.event.dto.response.DetailedEventResponseDto;
import com.env_protection.event.dto.response.LightweightEventResponseDto;
import com.env_protection.event.exception.EventNotFoundException;
import com.env_protection.event.exception.EventTypeNotFoundException;
import com.env_protection.event.model.Event;

import java.util.Collection;


/**
 * com.env_protection.event.service
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 1:57 PM
 * Description: ...
 */
public interface EventService {
    Event addEvent(CreateEventRequestDto event) throws EventTypeNotFoundException;
    boolean attendEvent(AttendEventRequestDto request);
    Collection<LightweightEventResponseDto> getLightweightEvents(int page, int size, String eventTypeId);
    DetailedEventResponseDto getDetailedEvent(String eventId) throws EventNotFoundException;
}
