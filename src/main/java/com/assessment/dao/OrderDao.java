package com.assessment.dao;

import com.assessment.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDao extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o JOIN o.customer c WHERE c.email = :email")
    Page<Order> findOrdersByCustomerEmail(String email, Pageable pageable);
}
