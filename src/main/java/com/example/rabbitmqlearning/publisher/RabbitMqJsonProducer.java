package com.example.rabbitmqlearning.publisher;

import com.example.rabbitmqlearning.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJsonProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String jsonRoutingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqJsonProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMqJsonProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(User user){
        LOGGER.info("Json message -> {}", user);
        rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, user);
    }

}
