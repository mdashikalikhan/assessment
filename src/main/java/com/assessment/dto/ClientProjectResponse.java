package com.assessment.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClientProjectResponse {
    private String message;
    private boolean result;
    private List<ClientProjectDto> data;
}
