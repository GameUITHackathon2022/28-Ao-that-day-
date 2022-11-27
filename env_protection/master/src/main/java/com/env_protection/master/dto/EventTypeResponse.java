package com.env_protection.master.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * com.env_protection.master.dto
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 3:33 PM
 * Description: ...
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventTypeResponse {
    private String id;
    private String name;
    //private String constraint;
    private String description;
    //private LocalDateTime createdAt;
}
