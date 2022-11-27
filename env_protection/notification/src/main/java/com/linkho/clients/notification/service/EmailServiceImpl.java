package com.linkho.clients.notification.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    public boolean sendMessage(
            String to, String subject, String text) {
        try
        {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@aothatday.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            return true;
        }
        catch (Exception e)
        {
            log.info(e.getMessage());
            return false;
        }
    }
}