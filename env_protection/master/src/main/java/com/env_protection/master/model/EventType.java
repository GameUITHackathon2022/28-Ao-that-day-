package com.env_protection.master.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * model
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 1:27 PM
 * Description: ...
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class EventType {
    @Id
    private String id;
    private String name;
    private String eventConstraint;
    private String imgPath;
    @Column(length = 10000)
    private String description;
    private LocalDateTime createdAt;
}