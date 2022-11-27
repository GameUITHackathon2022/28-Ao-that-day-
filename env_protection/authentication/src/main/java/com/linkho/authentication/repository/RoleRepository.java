package com.linkho.authentication.repository;

import com.linkho.authentication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * com.linkho.authentication.com.env_protection.event.repository
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 1:27 AM
 * Description: ...
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
