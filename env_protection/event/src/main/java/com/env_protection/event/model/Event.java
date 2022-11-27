package com.env_protection.event.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Event {
    @Id
    private String id;
    private String title;
    private String location;
    private String eventTypeId;
    private String eventTypeName;
    private String eventTypeDescription;
    private String publisherName;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime endedAt;
    private LocalDateTime closeFormAt;
    private Long maxAttempt;
}
