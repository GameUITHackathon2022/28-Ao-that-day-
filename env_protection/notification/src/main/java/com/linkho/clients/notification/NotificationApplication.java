package com.linkho.clients.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * notification
 * Created by NhatLinh - 19127652
 * Date 11/6/2022 - 9:04 PM
 * Description: ...
 */
@SpringBootApplication(
        scanBasePackages = {
                "com.linkho.clients.notification",
                "org.linkho.amqp"
        }
)
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
}
