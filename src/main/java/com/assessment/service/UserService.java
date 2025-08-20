package com.assessment.service;

import com.assessment.dao.h2.UserDao;
import com.assessment.entity.h2.User;
import com.assessment.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class    UserService {
    private final UserDao userDao;

    public User getUserById(Long id){
        return userDao.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }

    public User createUser(User user){
        return userDao.save(user);
    }
}
