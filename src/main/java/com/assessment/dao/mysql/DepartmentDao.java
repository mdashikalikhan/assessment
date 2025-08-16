package com.assessment.dao.mysql;

import com.assessment.entity.mysql.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentDao extends JpaRepository<Department,Long> {

    //Fetch to avoid N+1
    @EntityGraph(attributePaths = "employees")
    List<Department> findAll();

    @Query(value = "select * from department", nativeQuery = true)
    List<Department> findAllDepartmentOnly();

    @Query(value = "select count(1) from department", nativeQuery = true)
    Long getDepartmentCount();
}
