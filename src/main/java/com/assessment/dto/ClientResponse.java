package com.assessment.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClientResponse {
    private String message;
    private Boolean result;
    private List<ClientDto> data;
}
