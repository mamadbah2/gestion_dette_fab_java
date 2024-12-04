package com.baf.data.repositories;

import java.util.ArrayList;
import java.util.List;

import com.baf.data.entities.Debt;
import com.baf.data.entities.User;
import com.baf.data.repositories.interfaces.UserInterf;

public class UserList implements UserInterf{

        private List<User> users = new ArrayList<>();

    @Override
    public void createUser(User user) {
       users.add(user);
    }

    @Override
    public void toggleUser(User user) {
        for (User u : users) {
            if (u.getIdUser() == user.getIdUser()) {
                u.setIsActive(!u.getIsActive());
                return;
            }
        }
        throw new IllegalArgumentException("User not found");
    }

    @Override
    public User getUserById(int userId) {
        for (User u : users) {
            if (u.getIdUser() == userId) {
                return u;
            }
        }
        return null;
    }

}
