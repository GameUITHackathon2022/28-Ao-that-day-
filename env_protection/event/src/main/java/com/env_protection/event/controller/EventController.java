package com.env_protection.event.controller;

import com.env_protection.event.dto.request.AttendEventRequestDto;
import com.env_protection.event.dto.request.CreateEventRequestDto;
import com.env_protection.event.dto.response.DetailedEventResponseDto;
import com.env_protection.event.dto.response.LightweightEventResponseDto;
import com.env_protection.event.exception.EventNotFoundException;
import com.env_protection.event.exception.EventTypeNotFoundException;
import com.env_protection.event.model.Event;
import com.env_protection.event.service.EventService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * com.env_protection.event.controller
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 1:53 PM
 * Description: ...
 */
@RestController
@RequestMapping("api/v1/event")
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<?> getEvents(@RequestParam int page, @RequestParam int size,
                                       @RequestParam(required = false) String eventTypeId)
    {
        Collection<LightweightEventResponseDto> res = eventService.getLightweightEvents(page, size, eventTypeId);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("attend")
    public ResponseEntity<?> attendEvent(@RequestBody AttendEventRequestDto request)
    {
        boolean attend = eventService.attendEvent(request);
        if (attend)
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("{eventId}")
    public ResponseEntity<?> getEvent(@PathVariable String eventId)
    {
        try
        {
            DetailedEventResponseDto res = eventService.getDetailedEvent(eventId);
            return ResponseEntity.ok().body(res);
        }
        catch (EventNotFoundException exception)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<?> addEvent(@RequestBody CreateEventRequestDto request)
    {
        try
        {
            Event res = eventService.addEvent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }
        catch (EventTypeNotFoundException exception)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
