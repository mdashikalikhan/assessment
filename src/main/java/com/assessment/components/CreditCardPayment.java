package com.assessment.components;

import org.springframework.stereotype.Component;

@Component("CREDIT")
public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(double payment) {
        System.out.println("paid " + payment
        + " via credit card");
    }

    @Override
    public String getType() {
        return "CREDIT";
    }
}
