package com.multichoice.test.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multichoice.test.dao.CustomerRepository;
import com.multichoice.test.model.Customer;


@RestController
@RequestMapping("/Customers")
public class CustomerController{

    @Autowired
    CustomerRepository customerRepository;
    
 
	@PostMapping(path = "/Add Customer" , consumes = "application/json")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        try {

                Customer customerAdd = this.customerRepository.save(customer);

                return new ResponseEntity<>(customerAdd, HttpStatus.CREATED);


        } catch (Exception e){
            return new ResponseEntity(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping(path = "/Update Customer details",consumes = "application/json" ,produces = "application/json")
    public ResponseEntity<Customer> updateCustomerDetails(@RequestBody Customer customer){

        if(customerRepository.findById(customer.getId()).isPresent()){
        	
        	 System.out.println("------------------");
        	
            Customer customer2 = customerRepository.findById(customer.getId()).get();
            customer2.setFirstName(customer.getFirstName());
            customer2.setLastName(customer.getLastName());
            customer2.setGender(customer.getGender());
            customerRepository.save(customer2);
            
            return new ResponseEntity<Customer>(customer2, HttpStatus.OK);
        }else{
            System.out.println("Customer Does'nt exist");
            return new ResponseEntity("Changes made",HttpStatus.BAD_REQUEST);
        }

      
    }


    @DeleteMapping(path = "/Delete Customer/{customernumber}", produces = "application/json")
    public ResponseEntity<Customer> deleteCustomer(@RequestParam("customernumber") int Id){
        this.customerRepository.deleteById(Id);
        return new ResponseEntity("User Deleted",HttpStatus.OK);
    }


    
    @GetMapping(path = "/Get Customer/{customernumber}", produces = "application/json")
    public ResponseEntity<Customer> getCustomerById(@RequestParam("customernumber") int Id){
       
        if(customerRepository.findById(Id).isPresent()){
       	
           Customer customer = customerRepository.findById(Id).get();
           
           return new ResponseEntity<Customer>(customer, HttpStatus.OK);
           
        }else{
            System.out.println("Customer Does'nt exist");
        }
        
        return new ResponseEntity("Ok",HttpStatus.OK);
     }

    
    @GetMapping(path = "/Get All Customers", produces = "application/json")
    public ResponseEntity<List<Customer>> getAllCustomers(){
       
           return  new ResponseEntity(customerRepository.findAll(), HttpStatus.OK);
     }

}
