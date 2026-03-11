package com.example.demo.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, FileEvent> kafkaTemplate;
    
    public KafkaProducer(KafkaTemplate<String, FileEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendFileEvent(FileEvent event) {
        kafkaTemplate.send("data-topic", event);
        System.out.println("===========================================");
        System.out.println("Demo: send message " + event.getActionType() + " for " + event.getJobId());
        System.out.println("===========================================");
    }
}
