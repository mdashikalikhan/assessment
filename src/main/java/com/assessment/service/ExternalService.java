package com.assessment.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class ExternalService {
    private RestTemplate restTemplate;

    public String getApiContents() {
        String url = "http://numbersapi.com/10";
        return restTemplate.getForObject(url, String.class);
    }
}
