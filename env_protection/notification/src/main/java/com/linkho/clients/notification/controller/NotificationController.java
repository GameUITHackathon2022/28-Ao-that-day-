package com.linkho.clients.notification.controller;

import com.linkho.clients.notification.NotificationRequest;
import com.linkho.clients.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * notification
 * Created by NhatLinh - 19127652
 * Date 11/6/2022 - 9:15 PM
 * Description: ...
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/notification")
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;
    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest)
    {
        notificationService.sendNotification(notificationRequest);
        log.info("send {} to customer {}", notificationRequest, notificationRequest.email());
    }
}
