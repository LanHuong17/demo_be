package com.example.demo.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.respone.DemoResponseEntity;
import com.example.demo.kafka.FileEvent;
import com.example.demo.kafka.KafkaProducer;
import com.example.demo.service.FileJobService;
import com.example.demo.utils.GenerateResponse;
import com.example.demo.utils.Messages;

import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/api/test")
public class FileJobController {
    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private FileJobService fileJobService;

    @GetMapping("/send")
    public String testSend() {
        FileEvent event = new FileEvent("vku-job-001", "READ_CSV", 
        "C:/Users/lanhu/Downloads/Bai tap SQL Co Ban.xls");
        kafkaProducer.sendFileEvent(event);
        return "Message send into Kafka! Check log.";
    }

    @PostMapping("/convert")
    public ResponseEntity<DemoResponseEntity> convertFile(@RequestParam MultipartFile multipartFile) {
        if (multipartFile.isEmpty() || !multipartFile.getOriginalFilename().endsWith(".xlsx")) {
            return GenerateResponse.generateResponse(false, Messages.FAILURE, HttpStatus.BAD_REQUEST);
        }
        try {
            String result = fileJobService.convertAndSave(multipartFile);
            return GenerateResponse.generateResponse(true, result, HttpStatus.ACCEPTED);
        } catch (IOException e) {
            return GenerateResponse.generateResponse(true, e.getMessage(), HttpStatus.ACCEPTED);
        }
    }
        
}
