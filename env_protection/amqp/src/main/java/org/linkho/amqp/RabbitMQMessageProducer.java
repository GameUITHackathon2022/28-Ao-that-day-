package org.linkho.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * org.linkho.amqp
 * Created by NhatLinh - 19127652
 * Date 11/12/2022 - 10:39 PM
 * Description: ...
 */
@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQMessageProducer {

    private final AmqpTemplate amqpTemplate;
    public void publish(String exchange, String routingKey, Object payload)
    {
        log.info("Publishing to {} using key {}. Payload {}", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to {} using key {}. Payload {}", exchange, routingKey, payload);
    }
}
