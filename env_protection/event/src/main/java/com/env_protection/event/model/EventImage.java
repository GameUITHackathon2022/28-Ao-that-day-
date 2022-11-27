package com.env_protection.event.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * com.env_protection.event.model
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 3:02 PM
 * Description: ...
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private Event event;
    private String imgPath;
}