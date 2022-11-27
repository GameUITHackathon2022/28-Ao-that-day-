package com.env_protection.storage.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


/**
 * com.env_protection.storage.service
 * Created by NhatLinh - 19127652
 * Date 11/27/2022 - 12:54 AM
 * Description: ...
 */

public interface StorageService {
    void save(MultipartFile file);
    Resource load(String filename);
}
