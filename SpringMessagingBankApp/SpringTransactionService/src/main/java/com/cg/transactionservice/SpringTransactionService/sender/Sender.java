package com.cg.transactionservice.SpringTransactionService.sender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.cg.transactionservice.SpringTransactionService.entity.Transaction;

@Component 
public class Sender {
	
	@Autowired
	private RabbitMessagingTemplate template;
	
	
	@Bean
	public Queue queue() {
		return new Queue("UpdateBalance", false);
	}
	
	public void send(Transaction transaction){
		template.convertAndSend("UpdateBalance", transaction);
	}
}
