package com.linkho.clients.notification.service;

/**
 * com.linkho.clients.notification.service
 * Created by NhatLinh - 19127652
 * Date 11/27/2022 - 2:22 AM
 * Description: ...
 */
public interface EmailService {
    boolean sendMessage(String to, String subject, String text);
}
