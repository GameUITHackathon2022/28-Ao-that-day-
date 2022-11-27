package com.env_protection.posting.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * com.env_protection.posting.dtos.response
 * Created by NhatLinh - 19127652
 * Date 11/27/2022 - 12:18 AM
 * Description: ...
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostingResponse {
    private String id;
    private String description;
    private String eventId;
    private Collection<String> imgPaths;
}
