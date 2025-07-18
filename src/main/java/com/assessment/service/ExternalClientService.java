package com.assessment.service;

import com.assessment.dto.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ExternalClientService {

    private final WebClient webClient;

    private CopyOnWriteArrayList<ClientDto> clients;


    private CopyOnWriteArrayList<EmployeeDto> employees;

    private CopyOnWriteArrayList<ClientProjectDto> projects;

    public ExternalClientService(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://freeapi.miniprojectideas.com/api/ClientStrive").build();
        clients = new CopyOnWriteArrayList<>(
                List.of(new ClientDto(100, "Contact1", "Company1", "Address1",
                                "City1", "pin1", "State1", 1, "gst1", "0100", "reg1"),
                        new ClientDto(200, "Contact2", "Company2", "Address2",
                                "City2", "pin2", "State2", 2, "gst2", "0200", "reg2"),
                        new ClientDto(300, "Contact3", "Company3", "Address3",
                                "City3", "pin3", "State3", 3, "gst3", "0300", "reg3"),
                        new ClientDto(400, "Contact4", "Company4", "Address4",
                                "City4", "pin4", "State4", 4, "gst4", "0400", "reg4"),
                        new ClientDto(500, "Contact5", "Company5", "Address5",
                                "City5", "pin5", "State5", 5, "gst5", "0500", "reg5"),
                        new ClientDto(600, "Contact6", "Company6", "Address6",
                                "City6", "pin6", "State6", 6, "gst6", "0600", "reg6"))
        );

        employees = new CopyOnWriteArrayList<>(
                List.of(new EmployeeDto("EMP1", "ID-1001", "Code-1001",
                                "emp1@gmail.com", "D-1", "R-1"),
                        new EmployeeDto("EMP2", "ID-2001", "Code-2001",
                                "emp2@gmail.com", "D-2", "R-2"),
                        new EmployeeDto("EMP3", "ID-3001", "Code-3001",
                                "emp3@gmail.com", "D-3", "R-3"),
                        new EmployeeDto("EMP4", "ID-4001", "Code-4001",
                                "emp4@gmail.com", "D-4", "R-4"),
                        new EmployeeDto("EMP5", "ID-5001", "Code-5001",
                                "emp5@gmail.com", "D-5", "R-5"),
                        new EmployeeDto("EMP6", "ID-6001", "Code-6001",
                                "emp6@gmail.com", "D-6", "R-6"))
        );

        projects = new CopyOnWriteArrayList<>(
                List.of(new ClientProjectDto(1001, "P-1",
                                "01-Jan-2010", "01-DEC-2010",
                                "ID-1001", "31-DEC-2011",
                                "S-1", "0100",
                                "100", "10000", "DET1"
                                , "p1@abc.com", "100", "Company1"),
                        new ClientProjectDto(2001, "P-2",
                                "01-Jan-2010", "01-DEC-2010",
                                "ID-2001", "31-DEC-2011",
                                "S-2", "0200",
                                "100", "10000", "DET2"
                                , "p2@abc.com", "200", "Company2"),
                        new ClientProjectDto(3001,"P-3",
                                "01-Jan-2010", "01-DEC-2010",
                                "ID-1003", "31-DEC-2011",
                                "S-3","0300",
                                "100","10000","DET3"
                                ,"p3@abc.com","300", "Company3"),
                        new ClientProjectDto(4001,"P-4",
                                "01-Jan-2010", "01-DEC-2010",
                                "ID-4001", "31-DEC-2011",
                                "S-4","0400",
                                "400","10000","DET4"
                                ,"p4@abc.com","400", "Company4"),
                        new ClientProjectDto(5001,"P-5",
                                "01-Jan-2010", "01-DEC-2010",
                                "ID-1001", "31-DEC-2011",
                                "S-5","0500",
                                "100","10000","DET5"
                                ,"p5@abc.com","500", "Company5"))

        );
    }

    public Mono<List<RoleDto>> getAllRoles() {
        return webClient.get().uri("/GetAllRoles")
                .retrieve()
                .bodyToMono(RoleResponse.class)
                .map(RoleResponse::getData);
    }

    public Mono<List<RoleDto>> getAllRolesV1() {
        List<RoleDto> lists
                = List.of(new RoleDto(1, "Senior Java Developer"),
                new RoleDto(2, "Lead Architect"),
                new RoleDto(3, "Junior Developer"),
                new RoleDto(3, "Devop Engineer"));
        return Mono.just(lists);
    }

    public Mono<DesignationResponse> getDesignations() {
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

    public Mono<ClientResponse> getClients() {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setMessage("All Clients");

        clientResponse.setData(
                clients
        );

        clientResponse.setResult(!clientResponse.getData().isEmpty());

        return Mono.just(clientResponse);
    }

    public boolean deleteClientById(int id) {
        ClientDto clientDto = clients.stream().filter(c -> c.getClientId() == id).findFirst()
                .orElse(null);
        if (clientDto == null) {
            return false;
        }
        clients.remove(clientDto);
        return true;
    }

    public ClientResponse updateClient(ClientDto clientDto) throws JsonMappingException {
        ClientResponse clientResponse = new ClientResponse();
        if (clientDto == null) {

            clientResponse.setResult(false);

            return clientResponse;
        }
        if (clientDto.getClientId() == 0) {
            clientResponse.setResult(true);
            ClientDto clientMax = clients.stream().max((c1, c2) -> c1.getClientId().compareTo(c2.getClientId())).orElse(
                    clientDto
            );

            int clientId = clientMax.getClientId() + 100;
            clientDto.setClientId(clientId);

            clients.add(clientDto);

            clientResponse.setData(clients);
            clientResponse.setMessage("Client Created");
            return clientResponse;


        } else {


            clients.stream().filter(c -> c.getClientId().equals(clientDto.getClientId()))
                    .forEach(
                            c -> {

                                c.setCity(clientDto.getCity());

                                c.setAddress(clientDto.getAddress());
                                c.setState(clientDto.getState());
                                c.setContactNo(clientDto.getContactNo());
                                c.setCompanyName(clientDto.getContactNo());
                                c.setGstNo(clientDto.getGstNo());
                                c.setEmployeeStrength(clientDto.getEmployeeStrength());
                                c.setContactPersonName(clientDto.getContactPersonName());
                                c.setPinCode(clientDto.getPinCode());
                                c.setRegNo(clientDto.getRegNo());
                            }
                    );

            clientResponse.setData(clients);
            clientResponse.setResult(true);
            clientResponse.setMessage("Client Updates");
            return clientResponse;


        }

    }

    public Mono<EmployeeResponse> getAllEmployees() {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setMessage("All Employees");
        employeeResponse.setData(employees);
        employeeResponse.setResult(true);
        return Mono.just(employeeResponse);
    }

    public Mono<ClientProjectResponse> getProjects() {
        ClientProjectResponse clientProjectResponse = new ClientProjectResponse();
        clientProjectResponse.setMessage("All Projects");
        clientProjectResponse.setData(projects);
        clientProjectResponse.setResult(true);
        return Mono.just(clientProjectResponse);
    }
}
