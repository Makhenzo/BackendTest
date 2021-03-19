package com.multichoice.test;



import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.multichoice.test.dao.CustomerRepository;
import com.multichoice.test.model.Customer;
import com.multichoice.test.services.CustomerService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

	
	@Autowired
	private CustomerService service;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	
	@Test
	public void getCustomersTest(){
				
			when(customerRepository.findAll()).thenReturn(Stream.of(new Customer(3,"Kennedy","Mah","Male")
					,new Customer(50,"uo","raseyu", "female")).collect(Collectors.toList()));
	        assertEquals(2, service.getCustomers().size());
	}
	
	
	@Test
	public void saveCustomerTest() {
		Customer customer = new Customer(51, "Liza", "VanBerg","Female");
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customer, service.addCustomer(customer));
	}
	
	
	@Test
	public void deleteCustomerTest() {
		Customer customer = new Customer(51, "Liza", "VanBerg","Female");
		service.deleteCustomer(customer.getId());
		verify(customerRepository, times(1)).deleteById(customer.getId());
	}
	
	
	@Test
	public void getCustomerById() {
		Customer customer = new Customer(3,"Kennedy","Mah","Male");
		service.getCustomerById(customer.getId());
		verify(customerRepository, times(1)).findById(customer.getId());
	}
	
	@Test
	public void updateCustomer() {
		Customer customer = new Customer(4,"Justin","Reddy","Male");
		
		if(customerRepository.findById(customer.getId()).isPresent()) {
			
			customer.setFirstName("FaithM");
			customer.setLastName("Manka");
			customer.setGender("female");
			
	 		
			when(customerRepository.save(customer)).thenReturn(customer);
			assertEquals(customer, service.addCustomer(customer));
			
		}else{
			System.out.println("TEST FAILED");
		}
	}
	

}
