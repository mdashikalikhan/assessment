package com.assessment;

import com.assessment.dao.h2.UserDao;
import com.assessment.dao.mysql.MySQLUserDao;
import com.assessment.entity.h2.User;
import com.assessment.model.MySqlUserModel;
import com.assessment.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private MySQLUserDao mySQLUserDao;


    private final ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup(){
        userService = new UserService(userDao, modelMapper, mySQLUserDao);
    }

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

    @Test
    void testGetMySQLUserById_Success(){
        com.assessment.entity.mysql.User user = new com.assessment.entity.mysql.User(
                "MD ASHIK ALI KHAN", "khan.ashik@gmail.com"
        );

        user.setId(1L);

        Mockito.when(mySQLUserDao.findById(1L))
                .thenReturn(Optional.of(user));

        MySqlUserModel mySQLUserById = userService.getMySQLUserById(1L);



        //Assertions.assertEquals(user.getId(), mySQLUserById.getId());
        Assertions.assertEquals(user.getName(), mySQLUserById.getName());

        Mockito.verify(mySQLUserDao, Mockito.times(1)).findById(1L);

    }
}
