package com.linkho.authentication.repository;

import com.linkho.authentication.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * com.linkho.authentication.com.env_protection.event.repository
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 1:19 AM
 * Description: ...
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
