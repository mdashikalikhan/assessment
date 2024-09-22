package com.assessment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customer")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", updatable = false)
    private Long customerId;

    @Column(name = "customer_name", nullable = false)
    @NonNull
    @NotEmpty(message = "Name is required")
    private String customerName;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_email", unique = true)
    @NonNull
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid Email Address")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer",fetch = FetchType.LAZY)
    private List<Order> orderList;

    /*@Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", email='" + email + '\'' +
                ", orderList=" + orderList +
                '}';
    }*/
}
