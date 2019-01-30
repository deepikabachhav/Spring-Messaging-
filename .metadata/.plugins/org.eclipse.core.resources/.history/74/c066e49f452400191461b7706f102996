package com.cg.bank.SpringBankProject.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bank.SpringBankProject.entity.Account;
import com.cg.bank.SpringBankProject.entity.SavingsAccount;
import com.cg.bank.SpringBankProject.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountResource {

	@Autowired
	private AccountService accountService;

	@GetMapping
	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> accounts = accountService.getAllAccounts();
		return new ResponseEntity<>(accounts, HttpStatus.OK);

	}

	@GetMapping("/{accountNumber}")
	public ResponseEntity<Account> getAccountById(@PathVariable int accountNumber) {
		Optional<Account> optionalAccount = accountService.getAccountById(accountNumber);
		Account account = optionalAccount.get();
		if (account == null) {
			return new ResponseEntity<>(account, HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@PostMapping
	public void addNewAccount(@RequestBody Account account) {
		accountService.addNewAccount(account);

	}

	@DeleteMapping("/{accountNumber}")
	public void deleteAccount(@PathVariable int accountNumber) {
		accountService.closeAccount(accountNumber);
	}
	
	@GetMapping("/{accountNumber}/balance")
	public ResponseEntity<Double> getAccountBalance(@PathVariable int accountNumber) {
		Optional<Account> optionalAccount=accountService.getAccountById(accountNumber);
 		Account account=optionalAccount.get();
		if(account==null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(account.getCurrentBalance(),HttpStatus.OK);		
	}

	@PutMapping("/{accountNumber}")
	public ResponseEntity<Double> updateAccountBalance(@PathVariable Integer accountNumber, @RequestParam Double currentBalance ) {
		Optional<Account> optionalAccount=accountService.getAccountById(accountNumber);
		Account account=optionalAccount.get();
		if(account==null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		account.setCurrentBalance(currentBalance);
		accountService.addNewAccount(account);
		return new ResponseEntity<>(account.getCurrentBalance(),HttpStatus.OK);		
	}
	/*
	 * @PutMapping("/{accountId}") public void
	 * updateAccount(@PathVariable("accountId") int accountNumber, @RequestParam
	 * String accountHolderName,
	 * 
	 * @RequestParam Boolean salary) { SavingsAccount savingAccount = null;
	 * savingAccount.setAccountHolderName(accountHolderName);
	 * savingAccount.setSalary(salary); accountService.updateAccount(savingAccount);
	 * 
	 * }
	 */

}
