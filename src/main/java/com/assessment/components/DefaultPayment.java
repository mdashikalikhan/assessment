package com.assessment.components;

import org.springframework.stereotype.Component;


public class DefaultPayment implements PaymentStrategy{
    @Override
    public void pay(double payment) {
        System.out.println("Invalid Payment type for Default Payment " + payment);
    }

    @Override
    public String getType() {
        return "DEFAULT";
    }
}
