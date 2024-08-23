package com.VotingManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.VotingManagementSystem.models.User;
import com.VotingManagementSystem.models.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

}
