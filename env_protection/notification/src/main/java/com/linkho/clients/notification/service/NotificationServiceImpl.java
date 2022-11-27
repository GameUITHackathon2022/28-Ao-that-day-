package com.linkho.clients.notification.service;

import com.linkho.clients.notification.NotificationRequest;
import com.linkho.clients.notification.model.Notification;
import com.linkho.clients.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * notification
 * Created by NhatLinh - 19127652
 * Date 11/6/2022 - 9:13 PM
 * Description: ...
 */
@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final EmailServiceImpl emailService;
    @Override
    public void sendNotification(NotificationRequest request) {
        if (emailService.sendMessage(request.email(), request.topic(), request.message()))
        {
            notificationRepository.save(
                    Notification.builder()
                            .customerEmail(request.email())
                            .message(request.message())
                            .createdAt(LocalDateTime.now())
                            .build()
            );
        }
    }
}


