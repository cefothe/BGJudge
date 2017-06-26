package com.cefothe.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cefothe on 08.06.17.
 */
@Configuration
public class AMQPConfiguration {

    @Value("${worker.queue}")
    private String workerQueue;

    @Value("${worker.exchange}")
    private String workerExchange;

    @Bean
    public Queue createWorkerQueue(){
        return new Queue(workerQueue);
    }

    @Bean
    public TopicExchange createWorkerExchange(){
        return new TopicExchange(workerExchange);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(workerQueue);
    }
}
