package com.env_protection.event.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * com.env_protection.event.dto.request
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 2:03 PM
 * Description: ...
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequestDto {
    private String publisherName;
    private String title;
    private String eventTypeId;
    private Long maxAttempt;
    private int dayCount;
    private String location;
    private Collection<String> imgPaths;
    private String description;
}