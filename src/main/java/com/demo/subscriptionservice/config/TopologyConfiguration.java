package com.demo.subscriptionservice.config;

import com.pivotal.rabbitmq.topology.ExchangeType;
import com.pivotal.rabbitmq.topology.TopologyBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class TopologyConfiguration {

    @Value("${rabbit.endpoints.standalone.exchange:emails}")
    String exchangeName;
    @Value("${rabbit.endpoints.standalone.queue:emails}")
    String queueName;

    @Bean
    public Consumer<TopologyBuilder> topology() {
        return (builder) -> builder
                .declareExchange(exchangeName)
                .type(ExchangeType.direct)
                .and()
                .declareQueue(queueName)
                .boundTo(exchangeName);
    }

}
