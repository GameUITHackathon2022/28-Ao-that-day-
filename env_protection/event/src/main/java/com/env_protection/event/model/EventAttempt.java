package com.env_protection.event.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * com.env_protection.event.model
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 2:56 PM
 * Description: ...
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "event_attempt",
        uniqueConstraints= @UniqueConstraint(columnNames = {"eventId", "username"}) )
public class EventAttempt {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String eventId;
    private String username;
    private LocalDateTime attemptedAt;

}
