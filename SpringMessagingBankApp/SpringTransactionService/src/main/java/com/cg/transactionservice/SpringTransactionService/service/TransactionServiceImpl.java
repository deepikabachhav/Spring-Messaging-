package com.cg.transactionservice.SpringTransactionService.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.transactionservice.SpringTransactionService.entity.Transaction;
import com.cg.transactionservice.SpringTransactionService.entity.TransactionType;
import com.cg.transactionservice.SpringTransactionService.repo.TransactionRepository;
@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
	
	
	public Double[] fundTransfer(Transaction senderTransaction,Transaction receiverTransaction) {
		receiverTransaction.setTransactionDetails(senderTransaction.getTransactionDetails());
		Double senderCurrentBalance=withdraw(senderTransaction.getAccountNumber(),senderTransaction.getTransactionDetails(),senderTransaction.getCurrentBalance(),senderTransaction.getAmount());
		Double receiverCurrentBalance=deposit(receiverTransaction.getAccountNumber(),receiverTransaction.getTransactionDetails(),receiverTransaction.getCurrentBalance(),senderTransaction.getAmount());
		return new Double[] {senderCurrentBalance,receiverCurrentBalance};
		
	}
	

	public Double deposit(int accountNumber,String transactionDetails,double currentBalance,double amount) {
		Transaction transaction=new Transaction();
		transaction.setAccountNumber(accountNumber);
		transaction.setAmount(amount);
		currentBalance+=amount;
		transaction.setCurrentBalance(currentBalance);
		transaction.setTransactionDate(LocalDateTime .now());
		transaction.setTransactionType(TransactionType.DEPOSIT);
		transactionRepository.save(transaction);
		return currentBalance;
		
	}

	public Double withdraw (int accountNumber, String transactionDetails,double currentBalance, double amount) {
		Transaction transaction=new Transaction();
		transaction.setAccountNumber(accountNumber);
		transaction.setAmount(amount);
		currentBalance-=amount;
		transaction.setCurrentBalance(currentBalance);
		transaction.setTransactionDate(LocalDateTime .now());
		transaction.setTransactionType(TransactionType.WITHDRAW);
		transactionRepository.save(transaction);
		return currentBalance;
	}


	@Override
	public List<Transaction> listOfTransactions() {
		return transactionRepository.findAll();
	}
}
