package com.env_protection.posting.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * com.env_protection.posting.dtos.request
 * Created by NhatLinh - 19127652
 * Date 11/27/2022 - 12:25 AM
 * Description: ...
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostingDto {
    private String description;
    private String eventId;
    private String[] imgPaths;
}
