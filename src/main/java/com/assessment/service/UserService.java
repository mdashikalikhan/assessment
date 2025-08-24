package com.assessment.service;

import com.assessment.dao.h2.UserDao;
import com.assessment.dao.mysql.MySQLUserDao;
import com.assessment.entity.h2.User;
import com.assessment.exception.UserNotFoundException;
import com.assessment.model.MySqlUserModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class    UserService {
    private final UserDao userDao;

    private final ModelMapper modelMapper;

    private final MySQLUserDao mysqlUserDao;

    public User getUserById(Long id){
        return userDao.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }

    public User createUser(User user){
        return userDao.save(user);
    }

    public MySqlUserModel getMySQLUserById(Long id){
        com.assessment.entity.mysql.User user = mysqlUserDao.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        return modelMapper.map(user, MySqlUserModel.class);
    }

}
