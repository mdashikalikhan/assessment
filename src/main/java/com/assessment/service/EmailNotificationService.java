package com.assessment.service;

import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}
