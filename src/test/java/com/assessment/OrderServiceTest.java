package com.assessment;

import com.assessment.dao.OrderDao;
import com.assessment.entity.Customer;
import com.assessment.entity.Order;
import com.assessment.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderDao orderDao;

    private Order order;
    private Customer customer;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        customer = new Customer("Md. Ashik Ali Khan", "khan.ashik@gmail.com");
        customer.setCustomerId(100l);
        order = new Order(BigDecimal.valueOf(6000),"Course 1",  LocalDate.now(), true,  customer);
        order.setOrderId(10l);
    }

    @Test
    void testCreateOrder(){
        when(orderDao.save(any(Order.class))).thenReturn(order);
        Order created = orderService.createOrder(order);
        assertNotNull(created);
        assertEquals("Course 1", created.getOrderDetails());
        assertEquals(true, created.isOrderActive());
    }

    @Test
    void testGetAllOrders(){
        Page<Order> page = new PageImpl<>(Arrays.asList(order));
        when(orderDao.findAll(any(Pageable.class))).thenReturn(page);
        Page<Order> retrieved = orderService.getAllOrders(PageRequest.of(0, 10));
        assertEquals(1, retrieved.getTotalElements());
        verify(orderDao, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testGetOrdersByCustomerEmail(){
        Page<Order> page = new PageImpl<>(Arrays.asList(order));
        when(orderDao.findOrdersByCustomerEmail(eq("khan.ashik@gmail.com"), any(Pageable.class))).thenReturn(page);
        Page<Order> result = orderService.getOrdersByCustomerEmail("khan.ashik@gmail.com", PageRequest.of(0, 10));
        assertEquals(1, result.getTotalElements());
        verify(orderDao, times(1)).findOrdersByCustomerEmail(eq("khan.ashik@gmail.com"), any(Pageable.class));
    }
}
