package com.assessment.components;

import com.assessment.service.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class NotficationManager {

    public final NotificationService notificationService;

    public NotficationManager(@Qualifier("smsService") NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void notifyUser(String message) {
        notificationService.send(message);
    }
}
