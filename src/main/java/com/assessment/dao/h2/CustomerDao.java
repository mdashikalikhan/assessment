package com.assessment.dao.h2;

import com.assessment.entity.h2.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
