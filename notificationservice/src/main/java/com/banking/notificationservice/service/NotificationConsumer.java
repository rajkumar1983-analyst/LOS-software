package com.banking.notificationservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = "loan-created", groupId = "notification-group")
    public void consume(String event) {
        System.out.println("NOTIFICATION : new loan event received -> " + event);
    }
}
