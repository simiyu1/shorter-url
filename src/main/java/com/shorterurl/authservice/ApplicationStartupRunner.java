package com.shorterurl.authservice;
import com.shorterurl.authservice.model.Role;
import com.shorterurl.authservice.model.User;
import com.shorterurl.authservice.service.RoleService;
import com.shorterurl.authservice.service.UserService;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) {
        createInitialRolesAndAdmin();
    }

    private void createInitialRolesAndAdmin() {
        // Create ROLE_USER
        Role roleUser = roleService.findByName(Role.RoleName.ROLE_USER)
        .orElseGet(() -> roleService.createRole(new Role(Role.RoleName.ROLE_USER)));

        // Create ROLE_ADMIN
        Role roleAdmin = roleService.findByName(Role.RoleName.ROLE_ADMIN)
        .orElseGet(() -> roleService.createRole(new Role(Role.RoleName.ROLE_ADMIN)));

        // Create admin user
        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword("Big#@RdPa$$");
        adminUser.setRoles(Set.of(roleAdmin));
        adminUser.setEmail("admin@ntoya.link");
        userService.registerAdminUser(adminUser);

        // Create regular user
        User regularUser = new User();
        regularUser.setUsername("user");
        regularUser.setPassword("Str0ngP@$$");
        regularUser.setRoles(Set.of(roleUser));
        regularUser.setEmail("user@ntoya.link");
        userService.registerUser(regularUser);
    }
}

