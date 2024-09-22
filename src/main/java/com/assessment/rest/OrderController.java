package com.assessment.rest;

import com.assessment.dao.OrderDao;
import com.assessment.entity.Order;
import com.assessment.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    private String getValidationErrors(BindingResult result){
        List<String> error = new ArrayList<>();
        result.getAllErrors().stream().forEach(
                s-> error.add(s.getDefaultMessage())
        );
        return error.toString();
    }

    @PostMapping
    public ResponseEntity createOrder(@Valid @RequestBody Order order,
                                             BindingResult result){
        if(result.hasErrors()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    getValidationErrors(result)
            );
        }
        try{
            Order createdOrder = orderService.createOrder(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error to create");
        }

    }

    // Get all orders with pagination - GET
    @GetMapping
    public ResponseEntity<Page<Order>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = orderService.getAllOrders(pageable);
        return ResponseEntity.ok(orders);
    }

    // Get order by ID - GET
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id){
        Order order = orderService.getOrderById(id)
                .orElseThrow(()->new RuntimeException("Not found with id " + id));;

        return ResponseEntity.ok(order);
    }

    // Update order - PUT
    @PutMapping("/{id}")
    public ResponseEntity updateOrder(@PathVariable long id, @Valid @RequestBody Order orderDetails,
                                             BindingResult result){

        if(result.hasErrors()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    getValidationErrors(result)
            );
        }

        try {
            Order updatedOrder = orderService.updateOrder(id, orderDetails);
            return new ResponseEntity<>(updatedOrder, HttpStatus.ACCEPTED);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error to update");
        }
    }

    // Delete order - DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable long id){
        try{
            orderService.deleteOrder(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-customer-email")
    public ResponseEntity<Page<Order>> getOrdersByCustomerEmail(
            @RequestParam String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = orderService.getOrdersByCustomerEmail(email, pageable);
        return ResponseEntity.ok(orders);
    }

}
