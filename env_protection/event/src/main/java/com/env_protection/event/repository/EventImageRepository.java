package com.env_protection.event.repository;

import com.env_protection.event.model.Event;
import com.env_protection.event.model.EventImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * com.env_protection.event.repository
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 9:18 PM
 * Description: ...
 */
public interface EventImageRepository extends JpaRepository<EventImage, Long> {

    @Query(value = "SELECT * FROM event_image e WHERE e.event_id = ?1", nativeQuery = true)
    List<EventImage> findEventImagesByEventId(String eventId);
}
