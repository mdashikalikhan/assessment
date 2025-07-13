package com.assessment.service;

import com.assessment.dto.DesignationDto;
import com.assessment.dto.DesignationResponse;
import com.assessment.dto.RoleDto;
import com.assessment.dto.RoleResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ExternalClientService {

    private final WebClient webClient;

    public ExternalClientService(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://freeapi.miniprojectideas.com/api/ClientStrive").build();
    }

    public Mono<List<RoleDto>> getAllRoles(){
        return webClient.get().uri("/GetAllRoles")
                .retrieve()
                .bodyToMono(RoleResponse.class)
                .map(RoleResponse::getData);
    }

    public Mono<List<RoleDto>> getAllRolesV1(){
        List<RoleDto> lists
                = List.of(new RoleDto(1, "Senior Java Developer"),
                new RoleDto(2, "Lead Architect"),
                new RoleDto(3, "Junior Developer"),
                new RoleDto(3, "Devop Engineer"));
        return Mono.just(lists);
    }

    public Mono<DesignationResponse> getDesignations(){
        List<DesignationDto> lists
                = List.of(new DesignationDto(100, "Manager"),
                new DesignationDto(200, "Lead Architect"),
                new DesignationDto(300, "Senior Full Stack Developer"),
                new DesignationDto(400, "Senior Back End Developer"),
                new DesignationDto(500, "UI/UX Designer"));
        DesignationResponse designationResponse = new DesignationResponse();
        designationResponse.setData(lists);
        designationResponse.setResult(true);
        designationResponse.setMessage("All Designations");
        return Mono.just(designationResponse);
    }
}
