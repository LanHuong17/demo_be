package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.kafka.FileEvent;
import com.example.demo.kafka.KafkaProducer;


@RestController
@RequestMapping("/api/test")
public class FileJobController {
    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/send")
    public String testSend() {
        FileEvent event = new FileEvent("vku-job-001", "READ_CSV", "D:/data/test.csv");
        kafkaProducer.sendFileEvent(event);
        return "Lệnh đã được gửi vào Kafka! Hãy check log ở Service B.";
    }
    
    
}
