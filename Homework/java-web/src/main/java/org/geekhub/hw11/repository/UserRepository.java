package org.geekhub.hw11.repository;

import org.geekhub.hw11.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> getUsers();

    User getUserById(long userId);

    User getUserByName(String userName);

    User getUserByEmail(String email);
}
