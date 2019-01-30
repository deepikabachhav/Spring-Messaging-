package com.moneymoney.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.moneymoney.web.circuitbreaker.TransactionServiceBreaker;
import com.moneymoney.web.entity.CurrentDataSet;
import com.moneymoney.web.entity.Transaction;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
public class BankAppController {

	@Autowired
	private RestTemplate restTemplate;
	
	private static CurrentDataSet storecurrentDataSet;
	/*
	 * @Autowired private TransactionServiceBreaker transactionServiceBreaker;
	 */

	@RequestMapping(value = "/")
	public String depositForm() {
		return "DepositForm";
	}

	@HystrixCommand(fallbackMethod = "depositFailure")
	@RequestMapping(value = "/deposit")
	public String deposit(@ModelAttribute Transaction transaction, Model model) {
		restTemplate.postForEntity("http://localhost:8989/transaction-Service/transactions", transaction, null);
		model.addAttribute("message", "Success!");
		return "DepositForm";
	}

	public String depositFailure(@ModelAttribute Transaction transaction, Model model) {
		model.addAttribute("message", "Deposit Service is Down or failed!!");
		return "DepositForm";
	}

	@RequestMapping("/withdraw")
	public String withdrawForm() {
		return "WithdrawForm";
	}

	@HystrixCommand(fallbackMethod = "withdrawFailure")
	@RequestMapping(value = "/withdrawAmount")
	public String withdraw(@ModelAttribute Transaction transaction, Model model) {
		restTemplate.postForEntity("http://localhost:8989/transaction-Service/transactions/withdraw", transaction,
				null);
		model.addAttribute("message", "Success!");
		return "WithdrawForm";
	}

	public String withdrawFailure(@ModelAttribute Transaction transaction, Model model) {
		model.addAttribute("message", "Withdraw Service is Down or failed!!");
		return "WithdrawForm";
	}

	@RequestMapping("/fundTransfer")
	public String fundTransferForm() {
		return "FundTransferForm";
	}

	@HystrixCommand(fallbackMethod = "fundTransferFailure")
	@RequestMapping(value = "/transferAmount")
	public String fundTransfer(@RequestParam("senderAccountNumber") Integer senderAccountNumber,
			@RequestParam("amount") Double amount, @RequestParam("receiverAccountNumber") Integer receiverAccountNumber,
			Model model) {
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(senderAccountNumber);
		transaction.setAmount(amount);

		restTemplate.postForEntity(
				"http://localhost:8989/transaction-Service/transactions/fundTransfer?receiverAccountNmber="
						+ receiverAccountNumber,
				transaction, null);
		model.addAttribute("message", "Success!");
		return "FundTransferForm";
	}

	public String fundTransferFailure(@RequestParam("senderAccountNumber") Integer senderAccountNumber,
			@RequestParam("amount") Double amount, @RequestParam("receiverAccountNumber") Integer receiverAccountNumber,
			Model model) {
		model.addAttribute("message", "FundTransfer Service is Down or failed!!");
		return "FundTransferForm";

	}

	@HystrixCommand(fallbackMethod = "statementFailure")
	@RequestMapping("/statement")
	public ModelAndView getStatement(@RequestParam("offset") int offset, @RequestParam("size") int size) {
		int currentSize = size == 0 ? 5 : size;
		int currentOffset = offset == 0 ? 1 : offset;
		Link previous = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BankAppController.class)
				.getStatement(currentOffset - currentSize, currentSize)).withRel("previous");
		Link next = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BankAppController.class)
				.getStatement(currentOffset + currentSize, currentSize)).withRel("next");
		CurrentDataSet currentDataSet = restTemplate
				.getForObject("http://localhost:8989/transaction-Service/transactions", CurrentDataSet.class);
		storecurrentDataSet= currentDataSet;
		List<Transaction> transactionList = currentDataSet.getTransactions();
		List<Transaction> transactions = new ArrayList<Transaction>();
		for (int value = currentOffset - 1; value < currentOffset + currentSize - 1; value++) {
			if ((transactionList.size() <= value && value > 0) || currentOffset < 1)
				break;
			Transaction transaction = transactionList.get(value);
			transactions.add(transaction);
		}
		/*
		 * currentDataSet.setPreviousLink(previous); currentDataSet.setNextLink(next);
		 * currentDataSet.setTransactions(transactions); return new
		 * ModelAndView("statements", "currentDataSet", currentDataSet);
		 */
		CurrentDataSet dataSet = new CurrentDataSet(transactions, previous,next);
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("currentDataSet", dataSet);
		modelView.setViewName("statements");
		return modelView;
	}
	
	public ModelAndView statementFailure(@RequestParam("offset") int offset, @RequestParam("size") int size) {
		int currentSize = size == 0 ? 5 : size;
		int currentOffset = offset == 0 ? 1 : offset;
		Link previous = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BankAppController.class)
				.getStatement(currentOffset - currentSize, currentSize)).withRel("previous");
		Link next = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BankAppController.class)
				.getStatement(currentOffset + currentSize, currentSize)).withRel("next");
		CurrentDataSet currentDataSet = storecurrentDataSet;
		List<Transaction> transactionList = currentDataSet.getTransactions();
		List<Transaction> transactions = new ArrayList<Transaction>();
		for (int value = currentOffset - 1; value < currentOffset + currentSize - 1; value++) {
			if ((transactionList.size() <= value && value > 0) || currentOffset < 1)
				break;
			Transaction transaction = transactionList.get(value);
			transactions.add(transaction);
		}
		CurrentDataSet dataSet = new CurrentDataSet(transactions, previous,next);
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("currentDataSet", dataSet);
		modelView.setViewName("statements");
		modelView.addObject("message", "Currently no infiormation is available.Try again later..!");
		return modelView;
	}
}
