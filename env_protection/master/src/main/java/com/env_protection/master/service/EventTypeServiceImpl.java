package com.env_protection.master.service;

import com.env_protection.master.dto.AddEventTypeDto;
import com.env_protection.master.dto.EventTypeResponse;
import com.env_protection.master.model.EventType;
import com.env_protection.master.repository.EventTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * com.env_protection.master.service
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 3:45 PM
 * Description: ...
 */
@Service
@Transactional
@AllArgsConstructor
public class EventTypeServiceImpl implements EventTypeService {
    private final EventTypeRepository eventTypeRepository;
    @Override
    public EventTypeResponse getEvent(String eventTypeId) {
        EventType eventType = eventTypeRepository.getById(eventTypeId);
        return EventTypeResponse.builder()
                .id(eventTypeId)
                .name(eventType.getName())
                .description(eventType.getDescription())
                .build();
    }

    @Override
    public EventType addEventType(AddEventTypeDto addEventTypeDto) {
        return eventTypeRepository.saveAndFlush(EventType.builder()
                        .id(addEventTypeDto.getId())
                        .name(addEventTypeDto.getName())
                        .eventConstraint(addEventTypeDto.getEventConstraint())
                        .description(addEventTypeDto.getDescription())
                        .imgPath(addEventTypeDto.getImgPath())
                        .createdAt(LocalDateTime.now())
                .build());
    }

    @Override
    public Collection<EventTypeResponse> getEvents() {
        return eventTypeRepository.findAll().stream().map(e -> EventTypeResponse.builder()
                .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .build()).collect(Collectors.toList());
    }

//    @PostConstruct
//    public void init()
//    {
//        eventTypeRepository.save(EventType
//                .builder()
//                .id("T01")
//                .name("Grow tree")
//                .createdAt(LocalDateTime.now())
//                .description("Grow as many tree as possible")
//                .build());
//        eventTypeRepository.save(EventType
//                .builder()
//                .id("G01")
//                .name("Collect trash")
//                .createdAt(LocalDateTime.now())
//                .description("Grow as many tree as possible")
//                .build());
//        eventTypeRepository.save(EventType
//                .builder()
//                .id("P01")
//                .name("Collect pin")
//                .createdAt(LocalDateTime.now())
//                .description("Grow as many tree as possible")
//                .build());
//    }
}
