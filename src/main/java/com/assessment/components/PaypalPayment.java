package com.assessment.components;

import org.springframework.stereotype.Component;

@Component("PAYPAL")
public class PaypalPayment implements PaymentStrategy{
    @Override
    public void pay(double payment) {
        System.out.println("Paid " + payment
        + "via PayPal");
    }

    @Override
    public String getType() {
        return "PAYPAL";
    }
}
