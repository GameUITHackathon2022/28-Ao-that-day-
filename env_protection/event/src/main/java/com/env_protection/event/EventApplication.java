package com.env_protection.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * com.env_protection.event
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 8:39 PM
 * Description: ...
 */
@SpringBootApplication(
        scanBasePackages = {
                "com.env_protection.event",
                "org.linkho.amqp"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.linkho.clients.master"
)
public class EventApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(EventApplication.class, args);
    }
}
