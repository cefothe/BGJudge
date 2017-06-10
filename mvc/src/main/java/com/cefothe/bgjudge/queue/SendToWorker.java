package com.cefothe.bgjudge.queue;

import com.cefothe.bgjudge.submissions.entities.Submission;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cefothe on 08.06.17.
 */
@Component
public class SendToWorker {

    private final RabbitTemplate rabbitTemplate;

    private final Queue queue;


    @Autowired
    public SendToWorker(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void sendMessage(Submission submission){
        this.rabbitTemplate.convertAndSend(queue.getName(),"test");
    }
}
