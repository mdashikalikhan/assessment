package com.assessment.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleResponse {
    private String message;
    private Boolean result;
    private List<RoleDto> data;
}
