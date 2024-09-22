package com.assessment.service;

import com.assessment.dao.OrderDao;
import com.assessment.entity.Order;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderDao orderDao;

    public Order createOrder(Order order){
        return orderDao.save(order);
    }

    public Page<Order> getAllOrders(Pageable pageable) {
        return orderDao.findAll(pageable);
    }

    public Optional<Order> getOrderById(long id) {
        return orderDao.findById(id);
    }

    @Transactional
    public Order updateOrder(long id, Order orderDetails) {
        Order order = orderDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found with id " + id));
        order.setOrderDetails(orderDetails.getOrderDetails());
        order.setOrderAmount(orderDetails.getOrderAmount());
        order.setOrderDate(orderDetails.getOrderDate());
        order.setOrderActive(order.isOrderActive());

        return orderDao.save(order);
    }

    @Transactional
    public void deleteOrder(Long id) {
        Order order = orderDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found with id " + id));
        orderDao.delete(order);
    }


    public Page<Order> getOrdersByCustomerEmail(String email, Pageable pageable) {
        return orderDao.findOrdersByCustomerEmail(email, pageable);
    }
}
