package org.geekhub.hw11.service;


import org.geekhub.hw11.entity.User;
import org.geekhub.hw11.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userService = new UserService(userRepository);
    }

    @Test
    void testGetAllUsers() {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User(1L, "user1", "user1@example.com"));
        expectedUsers.add(new User(2L, "user2", "user2@example.com"));

        when(userRepository.findAllUsers()).thenReturn(expectedUsers);

        List<User> actualUsers = userService.getAllUsers();

        assertEquals(expectedUsers.size(), actualUsers.size());
        assertEquals(expectedUsers.get(0), actualUsers.get(0));
        assertEquals(expectedUsers.get(1), actualUsers.get(1));
    }
}
