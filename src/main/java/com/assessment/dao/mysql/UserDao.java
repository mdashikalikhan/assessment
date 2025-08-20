package com.assessment.dao.mysql;

import com.assessment.entity.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

}
