package com.assessment.service;

import com.assessment.dao.CustomerDao;
import com.assessment.entity.Customer;
import com.assessment.entity.Order;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerDao customerDao;


    @Transactional
    public Customer createCustomer(Customer customer){
        return customerDao.save(customer);
    }

    public List<Customer> getAllCustomers(){
        List<Customer> all = customerDao.findAll();
        //System.out.println(all);
        return all;
    }

    public Optional<Customer> getCustomerById(long id){
        return customerDao.findById(id);
    }

    @Transactional
    public Customer updateCustomer(long id, Customer customer){
        Customer customerUpdate =
                customerDao.findById(id)
                        .orElseThrow(
                                ()->new RuntimeException("Not found with id " + id)
                        );
        customerUpdate.setCustomerAddress(customer.getCustomerAddress()!=null
        ?customer.getCustomerAddress(): null);
        customerUpdate.setCustomerName(customer.getCustomerName());
        customerUpdate.setEmail(customer.getEmail());
        Customer saved = customerDao.save(customerUpdate);
        return saved;
    }

    @Transactional
    public void deleteCustomer(long id){
        Customer customer =
                customerDao.findById(id)
                        .orElseThrow(
                                ()->new RuntimeException("Not found with id " + id)
                        );
        customerDao.delete(customer);
    }


}
