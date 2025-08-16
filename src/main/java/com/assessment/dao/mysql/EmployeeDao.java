package com.assessment.dao.mysql;

import com.assessment.entity.mysql.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
}
