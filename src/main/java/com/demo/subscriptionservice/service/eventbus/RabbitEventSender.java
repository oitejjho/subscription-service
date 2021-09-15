package com.demo.subscriptionservice.service.eventbus;

import com.demo.subscriptionservice.model.event.SubscriptionCompleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitEventSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    @Async("asyncTaskExecutor")
    public void send(SubscriptionCompleteEvent event) {
        rabbitTemplate.convertAndSend(exchange,routingkey, event);
    }
}
