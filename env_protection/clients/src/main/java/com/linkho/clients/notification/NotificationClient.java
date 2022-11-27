package com.linkho.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * com.linkho.clients.notification
 * Created by NhatLinh - 19127652
 * Date 11/6/2022 - 9:25 PM
 * Description: ...
 */
@FeignClient("notification")
public interface NotificationClient {
    @PostMapping("api/v1/notification")
    void sendNotification(@RequestBody NotificationRequest notificationRequest);
}
