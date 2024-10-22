package com.spring.navesespaciales_api.navesespaciales.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.spring.navesespaciales_api.navesespaciales.config.RabbitConfig;

@Component
public class RabbitConsumer {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("Mensaje recibido: " + message);
    }
}