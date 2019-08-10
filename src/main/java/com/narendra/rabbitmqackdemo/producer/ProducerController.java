package com.narendra.rabbitmqackdemo.producer;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/test/{abc}")
    public String test(@PathVariable(value = "abc") String abc){
        rabbitTemplate.convertAndSend("spring-boot-exchange","spring-boot-key", abc + " from RabbitMQ!");
        return  "abc";
    }
    @GetMapping(value = "/test1/{abc}")
    public String test1(@PathVariable(value = "abc") String abc){
        rabbitTemplate.convertAndSend("spring-boot-exchange","spring-boot-ke", abc + " from RabbitMQ!");
        return  "abc";
    }
    @GetMapping(value = "/test2/{abc}")
    public String test2(@PathVariable(value = "abc") String abc){
        rabbitTemplate.convertAndSend("spring-boot-exchange1","spring-boot-key", abc + " from RabbitMQ!");
        return  "abc";
    }
    @GetMapping(value = "/test3/{abc}")
    public String test3(@PathVariable(value = "abc") String abc){
        rabbitTemplate.convertAndSend("spring-boot-exchange","spring-boot-key", abc + " from RabbitMQ!");
        return  "abc";
    }

}