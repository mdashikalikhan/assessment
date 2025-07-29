package com.assessment;

import com.assessment.entity.User;
import com.assessment.rest.UserController;
import com.assessment.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testGetUserById(){
        User user = new User(1l, "Ashik", "khan.ashik@gmail");

        Mockito.when(userService.getUserById(1l)).thenReturn(user);

        ResponseEntity<User> result = userController.getUser(1l);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        Assertions.assertEquals("Ashik", result.getBody().getName(),"Matched");

    }

    @Test
    void testCreateUser(){

        User user = new User(null, "Ashik", "khan.ashik@gmail.com");

        User saved = new User(1l, "Ashik", "khan.ashik@gmail.com");

        Mockito.when(userService.createUser(user)).thenReturn(saved);

        ResponseEntity<User> result = userController.createUser(user);

        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertEquals(1l, result.getBody().getId());

    }
}
