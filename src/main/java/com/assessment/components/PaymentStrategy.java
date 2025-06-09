package com.assessment.components;

public interface PaymentStrategy {

    void pay(double payment);
    String getType();
}
