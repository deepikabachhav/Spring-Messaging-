package com.cg.bank.SpringBankProject.receiver;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.cg.bank.SpringBankProject.resource.AccountResource;
import com.cg.transactionservice.SpringTransactionService.entity.Transaction;

@Component
public class Receiver {
	
	@Autowired
	private AccountResource accountResource;
	
	@Bean
	public Queue queue() {
		return new Queue("UpdateBalance", false);
	}

	@RabbitListener(queues = "UpdateBalance")
	public void processMessage(Transaction transaction ) {
		System.out.println("Inside AccountReceiver");
		System.out.println(transaction.getCurrentBalance());
		accountResource.updateAccountBalance(transaction.getAccountNumber(), transaction.getCurrentBalance());
		
		
	}
}
