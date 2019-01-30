package com.cg.transactionservice.SpringTransactionService.service;

import java.util.List;

import com.cg.transactionservice.SpringTransactionService.entity.Transaction;

public interface TransactionService {

	Double[] fundTransfer(Transaction senderTransaction,Transaction receiverTransaction);

	Double deposit(int accountNumber, String transactionDetails, double currentBalance, double amount);

	Double withdraw(int accountNumber,String transactionDetails, double currentBalance, double amount);
	
	List<Transaction> listOfTransactions();
}
