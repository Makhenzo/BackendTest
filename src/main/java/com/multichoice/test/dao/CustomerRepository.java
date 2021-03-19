package com.multichoice.test.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.multichoice.test.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, Integer>{
	
	List<Customer> findById(String Id);
}