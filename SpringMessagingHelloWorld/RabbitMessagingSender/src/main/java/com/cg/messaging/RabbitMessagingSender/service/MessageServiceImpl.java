package com.cg.messaging.RabbitMessagingSender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.messaging.RabbitMessagingSender.sender.Sender;
@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private Sender sender;
	
	@Override
	public String messageWrite(String message) {
		sender.send(message);
		return message;
	}

}
