package com.assessment.rest;

import com.assessment.dto.ClientDto;
import com.assessment.dto.ClientResponse;
import com.assessment.dto.DesignationResponse;
import com.assessment.dto.RoleDto;
import com.assessment.service.ExternalClientService;
import com.assessment.service.ExternalService;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ClientRequest;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequestMapping("/external")
@AllArgsConstructor
@Slf4j
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

    @PostMapping("/clients")
    public ResponseEntity<ClientResponse> addClients(@RequestBody ClientDto clientDto){
        try {
            return ResponseEntity.ok(externalClientService.updateClient(clientDto));
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
            ClientResponse clientResponse = new ClientResponse();
            clientResponse.setMessage(e.getMessage());
            clientResponse.setResult(false);
            return ResponseEntity.ok(clientResponse);
        }
    }

    @GetMapping("/designations")
    public Mono<DesignationResponse> getDesignations(){
        return externalClientService.getDesignations();
    }

    @GetMapping("/clients")
    public Mono<ClientResponse> getClients(){
        return externalClientService.getClients();
    }

    @DeleteMapping("/clients/{clientId}")
    public Mono<ResponseEntity<String>> deleteClient(@PathVariable("clientId") Integer clientId){
        boolean deleted = externalClientService.deleteClientById(clientId);
        if(deleted){
            return Mono.just(ResponseEntity.ok("Client deleted"));
        } else {
            return Mono.just(ResponseEntity.notFound().build());
        }
    }

}
