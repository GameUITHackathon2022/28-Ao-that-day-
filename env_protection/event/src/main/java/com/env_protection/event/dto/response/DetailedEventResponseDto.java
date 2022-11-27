package com.env_protection.event.dto.response;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * com.env_protection.event.dto.response
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 1:58 PM
 * Description: ...
 */
public record DetailedEventResponseDto(String id, String name, String title, String publisherName,
                                       String location,
                                       String description, Collection<String> imgPaths, Long attendances,
                                       Long maxAttendances, LocalDateTime createdAt, LocalDateTime endedAt,
                                       LocalDateTime closeFormAt) {
}
