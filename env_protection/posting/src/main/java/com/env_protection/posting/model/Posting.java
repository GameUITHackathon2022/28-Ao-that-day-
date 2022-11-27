package com.env_protection.posting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * com.env_protection.posting.model
 * Created by NhatLinh - 19127652
 * Date 11/27/2022 - 12:11 AM
 * Description: ...
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Posting {
    @Id
    private String id;
    private String eventId;
    private String username;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
