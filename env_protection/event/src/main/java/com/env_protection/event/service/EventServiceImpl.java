package com.env_protection.event.service;


import com.env_protection.event.dto.request.AttendEventRequestDto;
import com.env_protection.event.dto.request.CreateEventRequestDto;
import com.env_protection.event.dto.response.DetailedEventResponseDto;
import com.env_protection.event.dto.response.LightweightEventResponseDto;
import com.env_protection.event.exception.EventNotFoundException;
import com.env_protection.event.exception.EventTypeNotFoundException;
import com.env_protection.event.model.Event;
import com.env_protection.event.model.EventAttempt;
import com.env_protection.event.model.EventImage;
import com.env_protection.event.repository.EventAttemptRepository;
import com.env_protection.event.repository.EventImageRepository;
import com.env_protection.event.repository.EventRepository;
import com.linkho.clients.master.EventTypeResponse;
import com.linkho.clients.master.MasterClient;
import com.linkho.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.linkho.amqp.RabbitMQMessageProducer;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * com.env_protection.event.service
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 2:13 PM
 * Description: ...
 */
@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventAttemptRepository eventAttemptRepository;
    private final EventImageRepository eventImageRepository;
    private final MasterClient masterClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;


    @Override
    public Event addEvent(CreateEventRequestDto event) throws EventTypeNotFoundException {
        ResponseEntity<EventTypeResponse> res = masterClient.getEvent(event.getEventTypeId());
        if (res.getStatusCode() != HttpStatus.OK)
            throw new EventTypeNotFoundException(event.getEventTypeId());
        EventTypeResponse eventType = res.getBody();
        String eventID = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        String des = event.getDescription();
        if (des == null)
            des = eventType.getDescription();
        Event newEvent = Event.builder()
                .id(eventID)
                .eventTypeId(eventType.getId())
                .eventTypeName(eventType.getName())
                .publisherName(event.getPublisherName())
                .description(des)
                .createdAt(now)
                .endedAt(now.plusDays(event.getDayCount()))
                .closeFormAt(now.plusDays(event.getDayCount()))
                .maxAttempt(event.getMaxAttempt())
                .title(event.getTitle())
                .location(event.getLocation())
                .eventTypeDescription(event.getDescription())
                .build();
        Event created = eventRepository.saveAndFlush(newEvent);
        for (String imgPath : event.getImgPaths())
        {
            eventImageRepository.save(EventImage
                    .builder()
                    .event(created)
                    .imgPath(imgPath)
                    .build());
        }
        eventImageRepository.flush();


        return created;
    }

    @Override
    public boolean attendEvent(AttendEventRequestDto request) {
        String evenId = request.getEventId();
        Long countCurrent = eventAttemptRepository.countEventAttemptsByEventId(evenId);
        Event event =  eventRepository.getById(evenId);
        if (countCurrent >= event.getMaxAttempt())
            return false;
        try
        {
            eventAttemptRepository.saveAndFlush(EventAttempt.builder()
                    .eventId(request.getEventId())
                    .username(request.getUsername())
                    .attemptedAt(LocalDateTime.now())
                    .build());
            NotificationRequest notificationRequest = new NotificationRequest(
                    request.getUsername(),
                    String.format("[Charge] Attend the event %s", event.getTitle()),
                    String.format("Hi %s, you has attended the event %s",
                            request.getUsername(), event.getTitle())
            );
            rabbitMQMessageProducer.publish(
                    "internal.exchange",
                    "internal.notification.routing-key",
                    notificationRequest
            );
            return true;
        }
        catch (DuplicateKeyException duplicateKeyException)
        {
            return false;
        }
    }

    @Override
    public Collection<LightweightEventResponseDto> getLightweightEvents(int page, int size, String evenTypeId) {
        Pageable firstPageWithTwoElements = PageRequest.of(page, size);
        Page<Event> allEvents = eventRepository.findAll(firstPageWithTwoElements);
        Stream<Event> eventStream = allEvents.stream();
        if (evenTypeId != null)
            eventStream = eventStream.filter(e -> e.getEventTypeId().equals(evenTypeId));
        FileSystem userDirectory = FileSystems.getDefault();
        return eventStream.map(e -> {
                    String img;
                    try {
                        List<EventImage> eventImageList = eventImageRepository.findEventImagesByEventId(e.getId());
//                        String path = userDirectory.getPath(
//                                .getImgPath())
//                                .toAbsolutePath().toString();
//                        FileInputStream file =
//                                new FileInputStream(path);
//                        byte[] imgArr = file.readAllBytes();
                        img = eventImageList.stream().findFirst().get().getImgPath();
                        //file.close();
                    } catch (Exception exception) {
                        log.info(exception.getMessage());
                        img = "";
                    }
                    Long countCurrent = eventAttemptRepository.countEventAttemptsByEventId(e.getId());
                    return new LightweightEventResponseDto(
                            e.getId(),
                            e.getEventTypeName(),
                            e.getTitle(),
                            e.getPublisherName(),
                            e.getLocation(),
                            e.getDescription(),
                            img,
                            countCurrent,
                            e.getMaxAttempt(),
                            e.getCreatedAt(),
                            e.getEndedAt(),
                            e.getCloseFormAt()
                    );
                }
            ).collect(Collectors.toList());
    }

    @Override
    public DetailedEventResponseDto getDetailedEvent(String eventId) throws EventNotFoundException {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isEmpty())
            throw new EventNotFoundException(eventId);
        var event = eventOptional.get();
        Long countCurrent = eventAttemptRepository.countEventAttemptsByEventId(eventId);
        List<EventImage> eventImageList = eventImageRepository.findEventImagesByEventId(event.getId());
        return new DetailedEventResponseDto(
                eventId,
                event.getEventTypeName(),
                event.getTitle(),
                event.getPublisherName(),
                event.getLocation(),
                event.getDescription(),
                eventImageList.stream().map(EventImage::getImgPath).collect(Collectors.toList()),
                countCurrent,
                event.getMaxAttempt(),
                event.getCreatedAt(),
                event.getEndedAt(),
                event.getCloseFormAt()
        );
    }
}
