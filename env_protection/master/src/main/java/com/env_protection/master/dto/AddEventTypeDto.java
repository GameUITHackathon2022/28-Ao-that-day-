package com.env_protection.master.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * com.env_protection.master.dto
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 8:01 PM
 * Description: ...
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddEventTypeDto {
    private String id;
    private String name;
    private String imgPath;
    private String eventConstraint;
    private String description;
}
