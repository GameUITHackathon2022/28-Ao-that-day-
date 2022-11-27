package com.linkho.clients.notification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * notification
 * Created by NhatLinh - 19127652
 * Date 11/6/2022 - 9:08 PM
 * Description: ...
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    @Id
    @SequenceGenerator(
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence"
    )
    @GeneratedValue(
            generator = "notification_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Integer id;
    private String customerEmail;
    private String message;
    private LocalDateTime createdAt;
}
