package com.narendra.rabbitmqackdemo.com.narendra.rabbitmqackdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ChannelListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;






//this is configuration for rabbitmq
@Configuration
@Slf4j
public class RabbitMQConfig {

    public final static String QUEUE_NAME = "spring-boot-queue";
    public final static String EXCHANGE_NAME = "spring-boot-exchange";
    public final static String ROUTING_KEY = "spring-boot-key";

    // Create a queue
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }

   // Create a topic type of switch
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // Use the routing key (routingKey) to bind the queue (Queue) to the switch (Exchange)
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
    @Bean
    public ConnectionFactory  connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost", 5672);
        connectionFactory.setUsername("narendra");
        connectionFactory.setPassword("narendra");

        connectionFactory.setPublisherReturns(true);
        connectionFactory.setPublisherConfirms(true);

        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(this.connectionFactory());
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->  {

                System.out.println("================");
                System.out.println("correlationData = " + correlationData);
                System.out.println("ack = " + ack);
                System.out.println("cause = " + cause);
                System.out.println("================");

        });

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) ->  {

                System.out.println("================");
                System.out.println("message = " + message);
                System.out.println("replyCode = " + replyCode);
                System.out.println("replyText = " + replyText);
                System.out.println("exchange = " + exchange);
                System.out.println("routingKey = " + routingKey);
                System.out.println("================");

        });

        return rabbitTemplate;
    }

}
