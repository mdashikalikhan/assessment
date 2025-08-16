package com.assessment.service;

import com.assessment.dao.mysql.DepartmentDao;
import com.assessment.entity.mysql.Department;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentDao  departmentDao;


    @Transactional("mySQLTransactionManager")
    public List<Department> getAllDepartmentsWithEmployees() {
        return departmentDao.findAll();
    }

    public Department getDepartmentById(Long id){
        return departmentDao.findById(id).orElseThrow(()->new RuntimeException("Id not found: " + id));
    }
}
