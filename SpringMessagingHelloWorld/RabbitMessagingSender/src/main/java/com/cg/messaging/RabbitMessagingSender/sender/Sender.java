package com.cg.messaging.RabbitMessagingSender.sender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component 
@Lazy
public class Sender {
	
	@Autowired
	RabbitMessagingTemplate template;
	
	@Autowired
	public Sender(RabbitMessagingTemplate template){
		this.template = template;
	}

	@Bean
	public Queue queue() {
		return new Queue("MessageQueue", false);
	}
	
	public void send(String message){
		template.convertAndSend("MessageQueue", message);
	}
}

