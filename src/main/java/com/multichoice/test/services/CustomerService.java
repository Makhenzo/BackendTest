package com.multichoice.test.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multichoice.test.dao.CustomerRepository;
import com.multichoice.test.model.Customer;

@Service
public class CustomerService{
	
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	
	public List<Customer> getCustomers() {
		 
		List<Customer> customers = customerRepository.findAll();
		System.out.println("Getting custoers from dataBase : " + customers );
		return customers;
	}
	
	public void deleteCustomer(int id) {
		customerRepository.deleteById(id);
	}

	public Optional<Customer> getCustomerById(int id) {
		return customerRepository.findById(id);
	}
	
	public Customer updateCustomer(Customer customer){
		return customerRepository.save(customer);
	}
	
}