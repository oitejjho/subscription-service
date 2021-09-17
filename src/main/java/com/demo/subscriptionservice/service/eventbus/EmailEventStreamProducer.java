package com.demo.subscriptionservice.service.eventbus;

import com.pivotal.rabbitmq.RabbitEndpointService;
import com.pivotal.rabbitmq.stream.ProducerStream;
import com.pivotal.rabbitmq.topology.TopologyBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class EmailEventStreamProducer {

    private static final Logger LOG = LogManager.getLogger(EmailEventStreamProducer.class);

    private final RabbitEndpointService rabbit;
    private final Consumer<TopologyBuilder> topology;

    @Value("${rabbit.endpoints.standalone.exchange:emails}")
    String exchangeName;

    public void save(String email) {
        Flux<String> emails = Flux
                .range(1, 1)
                .map(val -> email);

        ProducerStream<String> producerStream = rabbit
                .declareTopology(topology)
                .createProducerStream(String.class)
                .route()
                .toExchange(exchangeName)
                .then();

        producerStream
                .send(emails)
                .delayElements(Duration.ofMillis(5))
                .doOnNext(data -> LOG.info("email : {} is sent to queue", data))
                .subscribe();
    }

}
