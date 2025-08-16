package com.assessment.dao.h2;

import com.assessment.entity.h2.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}
