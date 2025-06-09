package com.assessment.components;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentFactory {


    private  final Map<String, PaymentStrategy> strategies;

    public PaymentFactory(Map<String, PaymentStrategy> strategies) {
        this.strategies = strategies;
        System.out.println(strategies);
    }

    public PaymentStrategy getStrategy(String strategy) {
        return strategies.getOrDefault(strategy, new DefaultPayment());
    }
}
