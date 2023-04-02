package com.shorterurl.authservice.repository;


import com.shorterurl.authservice.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(Role.RoleName roleName);
}