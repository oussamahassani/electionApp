package com.VotingManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.VotingManagementSystem.models.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
