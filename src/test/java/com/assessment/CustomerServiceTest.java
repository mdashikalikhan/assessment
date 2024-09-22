package com.assessment;

import com.assessment.dao.CustomerDao;
import com.assessment.entity.Customer;
import com.assessment.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerDao customerDao;

    private Customer customer;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        customer = new Customer("ashik", "khan.ashik@gmail.com");
        customer.setCustomerId(1l);
    }

    @Test
    void testCreateCustomer(){
        when(customerDao.save(any(Customer.class))).thenReturn(customer);
        Customer created = customerService.createCustomer(customer);
        assertNotNull(created);
        assertEquals("ashik", created.getCustomerName());
        assertEquals("khan.ashik@gmail.com", created.getEmail());
    }

    @Test
    void testGetAllCustomers(){
        List<Customer> customers = Arrays.asList(customer);
        when(customerDao.findAll()).thenReturn(customers);
        List<Customer> retrieved = customerService.getAllCustomers();
        assertEquals(1, retrieved.size());
        verify(customerDao, times(1)).findAll();
    }

    @Test
    void testGetCustomerById(){
        when(customerDao.findById(1L)).thenReturn(Optional.of(customer));
        Optional<Customer> retrieved = customerService.getCustomerById(1l);
        assertTrue(retrieved.isPresent());
        assertEquals("khan.ashik@gmail.com", retrieved.get().getEmail());
    }

    @Test
    void testUpdateCustomer(){
        Customer updatedCustomer = new Customer("Md. Ashik Ali Khan", "ashik.khan@sonaliintellect.com");
        when(customerDao.findById(1L)).thenReturn(Optional.of(customer));
        when(customerDao.save(any(Customer.class))).thenReturn(updatedCustomer);

        Customer result = customerService.updateCustomer(1L, updatedCustomer);
        assertEquals("Md. Ashik Ali Khan", result.getCustomerName());
        assertEquals("ashik.khan@sonaliintellect.com", result.getEmail());
    }

    @Test
    void testDeleteCustomer(){
        when(customerDao.findById(1L)).thenReturn(Optional.of(customer));
        doNothing().when(customerDao).delete(customer);
        customerService.deleteCustomer(1l);
        verify(customerDao, times(1)).delete(customer);
    }
}
