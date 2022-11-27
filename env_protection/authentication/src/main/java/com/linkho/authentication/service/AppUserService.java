package com.linkho.authentication.service;

import com.linkho.authentication.exception.*;
import com.linkho.authentication.model.AppUser;

import java.util.List;

/**
 * com.linkho.authentication.service
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 1:20 AM
 * Description: ...
 */
public interface AppUserService {
    AppUser save(AppUser appUser, String role) throws AppUserDuplicatedException;
    void linkRoleToUser(String username, String roleName) throws AppUserNotFoundException, RoleNotFoundException;
    AppUser findByUsername(String username);
    AppUser authenticate(String username, String password) throws AppUserNotFoundException, InvalidUsernameOrPasswordException;
    List<AppUser> findAll();
}

