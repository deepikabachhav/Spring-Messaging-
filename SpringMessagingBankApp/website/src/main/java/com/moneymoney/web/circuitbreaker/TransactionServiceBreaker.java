package com.moneymoney.web.circuitbreaker;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.moneymoney.web.entity.Transaction;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class TransactionServiceBreaker {
	private final RestTemplate restTemplate;

	public TransactionServiceBreaker(RestTemplate rest) {
		this.restTemplate = rest;
	}

	@HystrixCommand(fallbackMethod = "reliable")
	public ResponseEntity<Transaction> readDepositService(Transaction transaction) {
		URI uri = URI.create("http://localhost:8989/transaction-Service/transactions");
		return this.restTemplate.postForEntity(uri,transaction, null);
	}
	
	/*
	 * public ResponseEntity<Transaction> reliable(Transaction transaction) {
	 * System.out.println("Deposit service is down or failed !!!!"); return
	 * this.restTemplate.postForEntity(uri,transaction, null);
	 * 
	 * }
	 */
}
