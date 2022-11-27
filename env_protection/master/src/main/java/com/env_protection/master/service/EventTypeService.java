package com.env_protection.master.service;

import com.env_protection.master.dto.AddEventTypeDto;
import com.env_protection.master.dto.EventTypeResponse;
import com.env_protection.master.model.EventType;

import java.util.Collection;

/**
 * com.env_protection.master.service
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 3:44 PM
 * Description: ...
 */
public interface EventTypeService {
    EventTypeResponse getEvent(String eventTypeId);
    EventType addEventType(AddEventTypeDto addEventTypeDto);
    Collection<EventTypeResponse> getEvents();
}
