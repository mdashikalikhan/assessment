package com.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDto {
    String empName;
    String empId;
    String empCode;
    String empEmailId;
    String EmpDesignation;
    String role;
}
