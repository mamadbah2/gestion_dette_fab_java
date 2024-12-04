package com.baf.services;

import com.baf.data.entities.User;
import com.baf.data.repositories.interfaces.UserInterf;

public class UserServ {
    private UserInterf userInterf ;

    public UserServ(UserInterf userInterf) {
        this.userInterf = userInterf;
    }
    
    public void createUser(String email, String login, String password,  String role, boolean isActive) {
        User user = new User();
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        user.setIsActive(isActive);
        userInterf.createUser(user);
    }

    public User getUserById(int userId) {
        return userInterf.getUserById(userId);
    }

    public void toggleUser(int userId) {
        User user = getUserById(userId);
        if (user != null) {
            userInterf.toggleUser(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

}
