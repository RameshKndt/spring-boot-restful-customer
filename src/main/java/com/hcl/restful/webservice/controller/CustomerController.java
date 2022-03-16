package com.hcl.restful.webservice.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.restful.webservice.dao.CustomerDAO;
import com.hcl.restful.webservice.model.Customer;

@RestController
public class CustomerController {
	@Autowired
	private CustomerDAO customerDAO;
	
	@GetMapping("/hello")
	public String getMessage() {
		return "Good AfterNoon!";
	}
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return customerDAO.list();
	}
	@GetMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable Long id) {
		return customerDAO.get(id);
	}
	@DeleteMapping("/customers/{id}")
	public String deleteCustomer(@PathVariable Long id) {
		 customerDAO.delete(id);
		 return "Deleted Successfully";
	}
	@PostMapping(value = "/customers")
	public ResponseEntity createCustomer(@RequestBody Customer customer) {
		customerDAO.create(customer);
		return new ResponseEntity(customer, HttpStatus.OK);
	}
}
