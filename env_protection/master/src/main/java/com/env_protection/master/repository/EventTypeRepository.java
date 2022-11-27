package com.env_protection.master.repository;

import com.env_protection.master.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * com.env_protection.master.repository
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 3:35 PM
 * Description: ...
 */
@Repository
public interface EventTypeRepository extends JpaRepository<EventType, String> {

}
