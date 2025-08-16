package com.assessment.service;

import com.assessment.dao.h2.CustomerDao;
import com.assessment.entity.h2.Customer;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
