package com.assessment.components;

import com.assessment.service.NotificationService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DynamicNotificationManager {

    private final Map<String, NotificationService> notifications;

    public DynamicNotificationManager(Map<String, NotificationService> notifications) {
        this.notifications = notifications;
    }


}
