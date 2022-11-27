package com.env_protection.event.repository;

import com.env_protection.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * com.env_protection.event.repository
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 1:55 PM
 * Description: ...
 */
@Repository
public interface EventRepository extends JpaRepository<Event, String> {

}
