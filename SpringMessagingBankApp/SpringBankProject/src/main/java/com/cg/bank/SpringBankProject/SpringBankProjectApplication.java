package com.cg.bank.SpringBankProject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.cg.bank.SpringBankProject.entity.CurrentAccount;
import com.cg.bank.SpringBankProject.entity.SavingsAccount;
import com.cg.bank.SpringBankProject.service.AccountService;
@EnableDiscoveryClient
@SpringBootApplication
public class SpringBankProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBankProjectApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner populateData( AccountService accountService) {
		return (arg)->{
			accountService.addNewAccount(new SavingsAccount(100,"Deepika",23246.00,true));
			accountService.addNewAccount(new SavingsAccount(101,"Tejas",7890978.00,false));
			accountService.addNewAccount(new SavingsAccount(102,"ankita",23246.00,true));
			accountService.addNewAccount(new SavingsAccount(103,"tushar",2345.00,false));
			accountService.addNewAccount(new SavingsAccount(104,"dingding",4356.00,true));
			accountService.addNewAccount(new SavingsAccount(105,"rashmi",23246.00,false));
			accountService.addNewAccount(new CurrentAccount(106,"dingding",4356.00,123.00));
			accountService.addNewAccount(new CurrentAccount(107,"dingding",4356.00,4856.00));
			accountService.addNewAccount(new CurrentAccount(108,"dingding",4356.00,542.00));
			accountService.addNewAccount(new CurrentAccount(109,"dingding",4356.00,5421.00));
			
		
			
		};
	}

}

