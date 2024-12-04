package com.baf.data.repositories.interfaces;

import com.baf.data.entities.User;

public interface UserInterf {
    public void createUser(User user);
    public void toggleUser(User user);
    public User getUserById(int userId);
}
