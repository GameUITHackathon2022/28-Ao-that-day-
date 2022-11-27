package com.env_protection.event.dto.response;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * com.env_protection.event.dto.response
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 2:32 PM
 * Description: ...
 */
public record LightweightEventResponseDto(String id, String eventName, String title,
                                          String publisherName, String location, String description,
                                          String img, long attendances, long maxAttendances, LocalDateTime createdAt, LocalDateTime endedAt, LocalDateTime closeFormAt) {
}