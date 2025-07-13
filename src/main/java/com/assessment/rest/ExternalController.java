package com.assessment.rest;

import com.assessment.dto.DesignationResponse;
import com.assessment.dto.RoleDto;
import com.assessment.service.ExternalClientService;
import com.assessment.service.ExternalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequestMapping("/external")
@AllArgsConstructor
public class ExternalController {

    private final ExternalService externalService;

    private final ExternalClientService externalClientService;

    @GetMapping("/api")
    public ResponseEntity<String> getExternalContents(){
        String response = externalService.getApiContents();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/roles") public Mono<List<RoleDto>> getRoles(){
        return externalClientService.getAllRolesV1();
    }

    @GetMapping("/designations")
    public Mono<DesignationResponse> getDesignations(){
        return externalClientService.getDesignations();
    }
}
