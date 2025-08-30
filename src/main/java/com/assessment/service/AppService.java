package com.assessment.service;

import com.assessment.components.AppProperties;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppService {

    private final AppProperties appProperties;

    @PostConstruct
    private void printProperties(){
        System.out.println("App Properties: " + appProperties);
    }
}
