package com.env_protection.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * com.env_protection.storage
 * Created by NhatLinh - 19127652
 * Date 11/27/2022 - 1:01 AM
 * Description: ...
 */
@SpringBootApplication
@FeignClient
public class StorageApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(StorageApplication.class, args);
    }
}
