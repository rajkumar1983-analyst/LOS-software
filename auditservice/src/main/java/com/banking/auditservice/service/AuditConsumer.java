package com.banking.auditservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuditConsumer {

    @KafkaListener(topics = "loan-created", groupId = "audit-group")
    public void consume(String event) {
        System.out.println("AUDIT EVENT RECEIVED : " + event);
    }
}
