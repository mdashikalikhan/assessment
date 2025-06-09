package com.assessment.service;

import com.assessment.components.PaymentFactory;
import com.assessment.components.PaymentStrategy;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentFactory paymentFactory;

    public PaymentService(PaymentFactory paymentFactory) {
        this.paymentFactory = paymentFactory;
    }

    public void processPayment(String type, double amount) {
        PaymentStrategy paymentStrategy
                = paymentFactory.getStrategy(type);
        paymentStrategy.pay(amount);
    }
}
