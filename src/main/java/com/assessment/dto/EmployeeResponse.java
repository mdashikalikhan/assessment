package com.assessment.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeResponse {
    private String message;
    private Boolean result;
    private List<EmployeeDto> data;
}
