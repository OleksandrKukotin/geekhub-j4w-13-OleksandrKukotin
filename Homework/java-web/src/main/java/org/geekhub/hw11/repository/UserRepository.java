package org.geekhub.hw11.repository;

import org.geekhub.hw11.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> findAllUsers();
}
