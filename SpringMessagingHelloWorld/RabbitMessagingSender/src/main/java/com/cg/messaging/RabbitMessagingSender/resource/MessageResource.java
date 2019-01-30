package com.cg.messaging.RabbitMessagingSender.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.messaging.RabbitMessagingSender.service.MessageService;

@RestController
public class MessageResource {
	
	@Autowired
	private MessageService messageService;
	
	
	@RequestMapping("/hello")
	public String sendMessage(){
		return messageService.messageWrite("Hello deep!!");
	}
}
