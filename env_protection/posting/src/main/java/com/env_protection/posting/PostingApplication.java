package com.env_protection.posting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * com.env_protection.posting
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 10:13 PM
 * Description: ...
 */
@SpringBootApplication
@EnableFeignClients (
        basePackages = "com.linkho.clients.event"
)
public class PostingApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(PostingApplication.class, args);
    }
}
