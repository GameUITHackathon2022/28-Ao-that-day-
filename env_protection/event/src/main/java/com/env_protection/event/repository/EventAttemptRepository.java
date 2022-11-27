package com.env_protection.event.repository;

import com.env_protection.event.model.EventAttempt;
import com.env_protection.event.model.EventImagePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * com.env_protection.event.repository
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 2:59 PM
 * Description: ...
 */
public interface EventAttemptRepository extends JpaRepository<EventAttempt, String>
{
    @Query(value = "SELECT COUNT(DISTINCT e.username) FROM event_attempt e WHERE e.event_id = ?1", nativeQuery = true)
    Long countEventAttemptsByEventId(String eventId);
}
