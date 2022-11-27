package com.linkho.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * PACKAGE_NAME
 * Created by NhatLinh - 19127652
 * Date 11/25/2022 - 11:09 PM
 * Description: ...
 */
@SpringBootApplication
@EnableEurekaClient
public class AuthenticationApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
