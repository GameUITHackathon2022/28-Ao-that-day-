package com.linkho.clients.notification;

/**
 * notification
 * Created by NhatLinh - 19127652
 * Date 11/6/2022 - 9:16 PM
 * Description: ...
 */
public record NotificationRequest(
        String email,
        String topic,
        String message
) {
}
