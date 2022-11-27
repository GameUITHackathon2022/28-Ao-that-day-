package com.linkho.authentication.service;

import com.linkho.authentication.exception.RoleDuplicatedException;
import com.linkho.authentication.exception.RoleNotFoundException;
import com.linkho.authentication.model.Role;

/**
 * com.linkho.authentication.service
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 1:24 AM
 * Description: ...
 */
public interface RoleService {
    Role save(Role role) throws RoleDuplicatedException;

    Role findRoleByName(String name) throws RoleNotFoundException;
}

