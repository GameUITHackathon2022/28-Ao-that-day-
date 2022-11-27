package com.linkho.clients.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * com.env_protection.event.dto.request
 * Created by NhatLinh - 19127652
 * Date 11/27/2022 - 12:30 AM
 * Description: ...
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendEventRequestDto {
    private String username;
    private String eventId;
}
