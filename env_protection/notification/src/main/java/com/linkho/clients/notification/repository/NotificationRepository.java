package com.linkho.clients.notification.repository;

import com.linkho.clients.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * notification
 * Created by NhatLinh - 19127652
 * Date 11/6/2022 - 9:12 PM
 * Description: ...
 */
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}