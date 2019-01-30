package com.cg.transactionservice.SpringTransactionService.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.transactionservice.SpringTransactionService.entity.CurrentDataSet;
import com.cg.transactionservice.SpringTransactionService.entity.Transaction;
import com.cg.transactionservice.SpringTransactionService.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<Transaction> deposit(@RequestBody Transaction transaction){
		ResponseEntity<Double> entity=restTemplate.getForEntity("http://account-Service/accounts/"+transaction.getAccountNumber()+ "/balance",Double.class);
		Double currentBalance=entity.getBody();
		Double updateBalance=transactionService.deposit(transaction.getAccountNumber(), transaction.getTransactionDetails(), currentBalance, transaction.getAmount());
		restTemplate.put("http://account-Service/accounts/"+transaction.getAccountNumber()+"?currentBalance="+updateBalance,null);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<Transaction> withdraw(@RequestBody Transaction transaction){
		ResponseEntity<Double> entity=restTemplate.getForEntity("http://account-Service/accounts/"+transaction.getAccountNumber()+ "/balance",Double.class);
		Double currentBalance=entity.getBody();
		Double updateBalance=transactionService.withdraw(transaction.getAccountNumber(), transaction.getTransactionDetails(), currentBalance, transaction.getAmount());
		restTemplate.put("http://account-Service/accounts/"+transaction.getAccountNumber()+"?currentBalance="+updateBalance,null);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	@PostMapping("/fundTransfer")
	public ResponseEntity<Transaction> fundTransfer(@RequestBody Transaction senderTransaction,@RequestParam Integer receiverAccountNmber){
		Double[] updatedBalance=new Double[2];
		Transaction receiverTransaction=new Transaction();
		ResponseEntity<Double> entityOne=restTemplate.getForEntity("http://account-Service/accounts/"+senderTransaction.getAccountNumber()+ "/balance",Double.class);
		Double sendercurrentBalance=entityOne.getBody();
		ResponseEntity<Double> entityTwo=restTemplate.getForEntity("http://account-Service/accounts/"+receiverAccountNmber+ "/balance",Double.class);
		Double receivercurrentBalance=entityTwo.getBody();
		receiverTransaction.setCurrentBalance(receivercurrentBalance);
		senderTransaction.setCurrentBalance(sendercurrentBalance);
		receiverTransaction.setAccountNumber(receiverAccountNmber);
		updatedBalance=transactionService.fundTransfer(senderTransaction, receiverTransaction);
		restTemplate.put("http://account-Service/accounts/"+senderTransaction.getAccountNumber()+"?currentBalance="+updatedBalance[0],null);
		restTemplate.put("http://account-Service/accounts/"+receiverTransaction.getAccountNumber()+"?currentBalance="+updatedBalance[1],null);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<CurrentDataSet> listOfTransactions(){
		CurrentDataSet currentDataSet =new CurrentDataSet();
		List<Transaction> transactions = transactionService.listOfTransactions();
		currentDataSet.setTransactions(transactions);
		return new ResponseEntity<>(currentDataSet, HttpStatus.OK);
	}
}
