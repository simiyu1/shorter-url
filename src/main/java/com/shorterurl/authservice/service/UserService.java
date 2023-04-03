package com.shorterurl.authservice.service;

import com.shorterurl.authservice.model.Role;
import com.shorterurl.authservice.model.User;
import com.shorterurl.authservice.model.Role.RoleName;
import com.shorterurl.authservice.repository.RoleRepository;
import com.shorterurl.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.shorterurl.authservice.model.UserRegistrationResponse;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserRegistrationResponse registerUser(User user) {
        if (user.getEmail() != null) {
            Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                // User with the same email already exists, return a failed response
                return new UserRegistrationResponse(false, "User with the same email already exists.", null);
            }
        }
        
        // Set role to ROLE_USER if not found
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Role userRole = roleService.findByName(Role.RoleName.ROLE_USER)
                .orElseGet(() -> roleService.createRole(new Role(Role.RoleName.ROLE_USER)));
            user.setRoles(Set.of(userRole));
        }

        // If the email is unique or not provided (null), save the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return new UserRegistrationResponse(true, "User registration successful.", savedUser);
    }

    public UserRegistrationResponse registerAdminUser(User user) {
        if (user.getEmail() != null) {
            Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                // User with the same email already exists, return a failed response
                return new UserRegistrationResponse(false, "User with the same email already exists.", null);
            }
        }
        System.out.print(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(Role.RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("User Role not found."));
        user.setRoles(Collections.singleton(userRole));
        User savedUser = userRepository.save(user);
        return new UserRegistrationResponse(true, "User registration successful.", savedUser);
    }

    
}
