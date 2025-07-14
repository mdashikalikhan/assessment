package com.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Integer clientId;
    private String contactPersonName;
    private String companyName;
    private String address;
    private String city;
    private String pinCode;
    private String state;
    private Integer employeeStrength;
    private String gstNo;
    private String contactNo;
    private String regNo;
}
