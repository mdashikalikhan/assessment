package com.assessment.rest;

import com.assessment.service.ExternalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external")
@AllArgsConstructor
public class ExternalController {

    private ExternalService externalService;

    @GetMapping("/google")
    public ResponseEntity<String> getExternalContents(){
        String response = externalService.getApiContents();
        return ResponseEntity.ok(response);
    }
}
