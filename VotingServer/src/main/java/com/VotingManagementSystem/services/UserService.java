package com.VotingManagementSystem.services;

import java.util.List;

import com.VotingManagementSystem.DTO.SendEmail;
import com.VotingManagementSystem.models.Role;
import com.VotingManagementSystem.models.User;

public interface UserService {

    //changePassword
    public User updateUser(User user);

    //voter
    public User addUser(User user);
    public User getUser(String email);
    public User getUserById(Long userId);

    //admin
    public User addAdminUser(User user);
    public List<User> getAllUsers();
    public void deleteUser(String email);

	public void countctUs(SendEmail email);

	public List<Object[]> countUserByDate();

	public List<User> findlastThreeUser();

	public Long counUser();

	public User findUserByEmail(String username);
}
