package com.env_protection.master.controller;

import com.env_protection.master.dto.AddEventTypeDto;
import com.env_protection.master.dto.EventTypeResponse;
import com.env_protection.master.model.EventType;
import com.env_protection.master.repository.EventTypeRepository;
import com.env_protection.master.service.EventTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * com.env_protection.master.controller
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 3:29 PM
 * Description: ...
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/master")
public class MasterController {
    private final EventTypeService eventTypeRepository;
    @GetMapping("/eventType/{eventTypeId}")
    public ResponseEntity<EventTypeResponse> getEvent(@PathVariable String eventTypeId)
    {
        EventTypeResponse res = eventTypeRepository.getEvent(eventTypeId);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("eventType")
    public ResponseEntity<EventType> addEvent(@RequestBody AddEventTypeDto request)
    {
        EventType res = eventTypeRepository.addEventType(request);
        if (res == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("eventType")
    public ResponseEntity<Collection<EventTypeResponse>> getEvents()
    {
        Collection<EventTypeResponse> res = eventTypeRepository.getEvents();
        if (res == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
