package com.linkho.authentication.service;

import com.linkho.authentication.exception.RoleDuplicatedException;
import com.linkho.authentication.exception.RoleNotFoundException;
import com.linkho.authentication.model.Role;
import com.linkho.authentication.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) throws RoleDuplicatedException {
        if (roleRepository.findByName(role.getName()) != null) {
            log.error("Role with name: {} already exists", role.getName());
            throw new RoleDuplicatedException(role.getName());
        }
        log.info("Saving role with name: {}", role.getName());
        return roleRepository.saveAndFlush(role);
    }



    @Override
    public Role findRoleByName(String name) throws RoleNotFoundException {
        Role role = roleRepository.findByName(name);
        if (role== null)
            throw new RoleNotFoundException(name);
        return role;
    }
}
