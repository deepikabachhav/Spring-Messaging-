package com.cg.bank.SpringBankProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bank.SpringBankProject.entity.Account;
import com.cg.bank.SpringBankProject.entity.SavingsAccount;
import com.cg.bank.SpringBankProject.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<Account> getAllAccounts() {
	return accountRepository.findAll();
	}


	@Override
	public void addNewAccount(Account account) {
	 accountRepository.save(account);
	}

	@Override
	public Optional<Account> getAccountById(int accountNumber) {
		
		Optional<Account> account= accountRepository.findById(accountNumber);
		 return account;
	}


	@Override
	public void closeAccount(int accountNumber) {
		accountRepository.deleteById(accountNumber);
	}


	@Override
	public void updateAccount(Account savingsAccount) {
		accountRepository.save(savingsAccount);

	}

	@Override
	public void updateBalnce(Account account) {
		accountRepository.save(account);
	}
}
