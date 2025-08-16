package com.assessment.entity.mysql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
@RequiredArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dep_id")
    private Long id;

    @Column(name = "dep_name")
    @NonNull
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;

    public void addEmployee(Employee... employees) {
        if(this.employees == null) {
            this.employees = new ArrayList<Employee>();
        }
        for(Employee employee : employees) {
            this.employees.add(employee);
            employee.setDepartment(this);
        }


    }
}
