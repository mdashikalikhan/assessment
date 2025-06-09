package com.assessment.components;

import org.springframework.stereotype.Component;

@Component("UNIPAY")
public class UniPayment implements PaymentStrategy{
    @Override
    public void pay(double payment) {
        System.out.println("Paid " + payment
        + " via UniPay");
    }

    @Override
    public String getType() {
        return "UNIPAY";
    }
}
