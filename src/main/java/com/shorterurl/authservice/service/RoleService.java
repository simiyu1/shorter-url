package com.shorterurl.authservice.service;

import com.shorterurl.authservice.model.Role;
import com.shorterurl.authservice.repository.RoleRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> findByName(Role.RoleName roleName) {
        return roleRepository.findByName(roleName);
    }
}
