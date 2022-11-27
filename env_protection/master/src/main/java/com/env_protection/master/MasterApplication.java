package com.env_protection.master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * com.env_protection.master
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 6:46 PM
 * Description: ...
 */
@SpringBootApplication
@EnableEurekaClient
public class MasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterApplication.class, args);
    }
}
