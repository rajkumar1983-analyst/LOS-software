package com.banking.loanservice.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.banking.loanservice.events.LoanCreatedEvent;

@Service
public class LoanEventPublisher {

    private final KafkaTemplate<String, LoanCreatedEvent> kafkaTemplate;

    public LoanEventPublisher(
            KafkaTemplate<String, LoanCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishLoanCreated(LoanCreatedEvent event) {

        kafkaTemplate.send(
                "loan-created",
                event);

        System.out.println(
                "Published LoanCreatedEvent for Loan "
                        + event.getLoanId());
    }
}
