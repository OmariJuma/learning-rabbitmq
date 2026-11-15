package com.example.rabbitmqlearning.controller;

import com.example.rabbitmqlearning.dto.User;
import com.example.rabbitmqlearning.publisher.RabbitMqJsonProducer;
import com.example.rabbitmqlearning.publisher.RabbitMqProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
    private RabbitMqProducer producer;
    private RabbitMqJsonProducer jsonProducer;

    public MessageController(RabbitMqProducer producer, RabbitMqJsonProducer jsonProducer){
        this.producer = producer;
        this.jsonProducer = jsonProducer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to rabbitmq...");

    }

    @PostMapping("/publish/json")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        jsonProducer.sendMessage(user);
        return ResponseEntity.ok("Json message sent to rabbitmq...");
    }
}
