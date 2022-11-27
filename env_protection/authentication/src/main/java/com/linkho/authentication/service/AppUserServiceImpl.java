package com.linkho.authentication.service;

import com.linkho.authentication.exception.*;
import com.linkho.authentication.model.AppUser;
import com.linkho.authentication.model.Role;
import com.linkho.authentication.repository.AppUserRepository;
import com.linkho.authentication.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser save(AppUser appUser, String role) throws AppUserDuplicatedException {
        if (appUserRepository.findByUsername(appUser.getUsername()) != null) {
            log.error("User with username: {} not found", appUser.getUsername());
            throw new AppUserDuplicatedException(appUser.getUsername());
        }
          log.info("Saving user with username: {}", appUser.getUsername());
        try
        {
            Role roleName = roleService.findRoleByName(role);
            AppUser created = AppUser
                    .builder()
                    .username(appUser.getUsername())
                    .name(appUser.getUsername())
                    .password(passwordEncoder.encode(appUser.getPassword()))
                    .roles(new ArrayList<>(){
                        {
                            add(roleName);
                        }
                    })
                    .createdAt(LocalDateTime.now())
                    .build();
            return appUserRepository.saveAndFlush(created);
        }
        catch (RoleNotFoundException roleNotFoundException)
        {
            return null;
        }
    }

    @Override
    public void linkRoleToUser(String username, String roleName) throws AppUserNotFoundException, RoleNotFoundException {
        AppUser userFromDb = appUserRepository.findByUsername(username);
        if (userFromDb == null) {
            log.error("User with username: {} not found", username);
            throw new AppUserNotFoundException(username);
        }
        Role roleFromDb = roleRepository.findByName(roleName);
        if (roleFromDb == null) {
            log.error("Role with name: {} not found", roleName);
            throw new RoleNotFoundException(roleName);
        }
        userFromDb.getRoles().add(roleFromDb);
        log.info("Added role: {} to user: {}", roleName, username);
    }

    @Override
    public AppUser findByUsername(String username) {
        log.info("Retrieving user: {}", username);
        return appUserRepository.findByUsername(username);
    }

    @Override
    public AppUser authenticate(String username, String password) throws AppUserNotFoundException, InvalidUsernameOrPasswordException {
        AppUser user = findByUsername(username);
        if (user == null)
            throw new AppUserNotFoundException(username);
        boolean checkPass = passwordEncoder.matches(password, user.getPassword());
        if (!checkPass)
            throw new InvalidUsernameOrPasswordException(username);
        return user;
    }

    @Override
    public List<AppUser> findAll() {
        log.info("Retrieving all users");
        return appUserRepository.findAll();
    }

//    @PostConstruct
//    public void init() throws RoleDuplicatedException, AppUserDuplicatedException {
//        try
//        {
//            roleService.save(Role.builder()
//                    .name("OrdinaryUser")
//                    .build());
//            roleService.save(Role.builder()
//                    .name("Publisher")
//                    .build());
//        }
//        catch (RoleDuplicatedException roleDuplicatedException)
//        {
//            log.info("Role has been created {}", roleDuplicatedException.getName());
//        }
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser userFromDb = appUserRepository.findByUsername(username);
        if (userFromDb == null) {
            log.error("User : {} is nowhere to be found", username);
            throw new UsernameNotFoundException("User : " + username + " is nowhere to be found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userFromDb.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName()))
        );
        return new User(userFromDb.getUsername(), userFromDb.getPassword(), authorities);
    }
}
