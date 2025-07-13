package com.assessment.dto;

import lombok.Data;

import java.util.List;

@Data
public class DesignationResponse {
    private String message;
    private Boolean result;
    private List<DesignationDto> data;
}
