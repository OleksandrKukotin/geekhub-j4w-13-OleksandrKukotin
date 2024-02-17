package org.geekhub.hw11.service;

import org.geekhub.hw11.entity.User;
import org.geekhub.hw11.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAllUsers();
    }
}
