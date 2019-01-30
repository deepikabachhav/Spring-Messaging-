package com.cg.bank.SpringBankProject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.bank.SpringBankProject.entity.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, Integer>{
	
}
