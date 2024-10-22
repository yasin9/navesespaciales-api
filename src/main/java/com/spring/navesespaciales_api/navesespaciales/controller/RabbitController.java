package com.spring.navesespaciales_api.navesespaciales.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.navesespaciales_api.navesespaciales.rabbit.RabbitProducer;

@RestController
@RequestMapping("/api/v1/rabbit")
public class RabbitController {

    private final RabbitProducer rabbitProducer;

    public RabbitController(RabbitProducer rabbitProducer) {
        this.rabbitProducer = rabbitProducer;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        rabbitProducer.sendMessage(message);
        return "Mensaje enviado a RabbitMQ: " + message;
    }
}