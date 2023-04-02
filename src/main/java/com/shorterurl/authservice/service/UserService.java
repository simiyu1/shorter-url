package com.shorterurl.authservice.service;

import com.shorterurl.authservice.model.Role;
import com.shorterurl.authservice.model.User;
import com.shorterurl.authservice.repository.RoleRepository;
import com.shorterurl.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(Role.RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("User Role not found."));
        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user);
    }
}
