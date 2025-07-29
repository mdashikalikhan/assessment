package com.assessment;

import com.assessment.dao.UserDao;
import com.assessment.entity.User;
import com.assessment.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserById_SUXS(){
        User user = new User(1l, "ASHIK", "khan.ashik@gmail.com");

        Mockito.when(userDao.findById(1l)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1l);

        Assertions.assertEquals(user, result);

    }

    @Test
    void testUserIdNotFound(){
        Mockito.when(userDao.findById(1l)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, ()->userService.getUserById(2l));
    }

    @Test
    void testCreateUser(){
        User user = new User(null, "Bob", "bob@gmail.com");
        User saved = new User(2l, "Bob", "bob@gmail.com");

        Mockito.when(userDao.save(user)).thenReturn(saved);

        User result = userService.createUser(user);

        Assertions.assertEquals(saved, result);

        Assertions.assertEquals(2l, result.getId());
    }
}
