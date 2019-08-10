package com.narendra.rabbitmqackdemo.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
@Component
@Slf4j
public class Consumer {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "spring-boot-queue",durable = "true"),
    exchange = @Exchange(value = "spring-boot-exchange",type = ExchangeTypes.TOPIC),key = "spring-boot-key"))
    public void consumeMessage(Message message, Channel channel) {

        log.info("consume message {}", message.getBody());
        log.info("delivery tag{}",message.getMessageProperties().getDeliveryTag());
    }
}