package com.assessment.rest;

import com.assessment.entity.Customer;
import com.assessment.entity.Order;
import com.assessment.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    /*@InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }*/

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> allCustomers = customerService.getAllCustomers();
        if(allCustomers==null || allCustomers.size()==0){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(allCustomers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id){
        Customer customerById = customerService.getCustomerById(id)
                .orElseThrow(()->new RuntimeException("Not found with id " + id));
        return ResponseEntity.ok(customerById);

    }

    private String getValidationErrors(BindingResult result){
        List<String> error = new ArrayList<>();
        result.getAllErrors().stream().forEach(
                s-> error.add(s.getDefaultMessage())
        );
        return error.toString();
    }

    @PostMapping
    public ResponseEntity createCustomer(@Valid @RequestBody
                                                   Customer customer,
                                                   BindingResult result){
        if(result.hasErrors()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    getValidationErrors(result)
            );
        }
        try{
            Customer customerCreated = customerService.createCustomer(customer);
            return new ResponseEntity<>(customerCreated, HttpStatus.CREATED);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error to create");
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity updateCustomer(@PathVariable long id,
                                         @Valid @RequestBody Customer customer,
                                                   BindingResult result){

        if(result.hasErrors()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    getValidationErrors(result)
            );
        }

        try{
            Customer customerUpdated = customerService.updateCustomer(id, customer);
            return new ResponseEntity<>(customerUpdated, HttpStatus.ACCEPTED);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error to update");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable long id){
        try{
            customerService.deleteCustomer(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }


}
