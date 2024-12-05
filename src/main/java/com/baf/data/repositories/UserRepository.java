package com.baf.data.repositories;

import com.baf.core.Repository;
import com.baf.data.entities.User;

public interface UserRepository extends Repository<User> {
    public void toggleUser(User user);
    public User getUserById(int userId);
}
