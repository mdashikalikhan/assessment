package com.assessment.entity.mysql;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "employee")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="emp_id")
    private Long id;

    @Column(name = "emp_name")
    @NonNull
    private String name;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "dep_id")
    private Department department;

    @NonNull
    private BigDecimal salary;
}
