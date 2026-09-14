package com.subhakar.springbootlearning.order_management.service;

import com.subhakar.springbootlearning.order_management.dto.Customer.CreateCustomerRequest;
import com.subhakar.springbootlearning.order_management.dto.Customer.CreateCustomerResponse;
import com.subhakar.springbootlearning.order_management.dto.Customer.GetCustomerResponse;
import com.subhakar.springbootlearning.order_management.dto.Customer.UpdateCustomerRequest;
import com.subhakar.springbootlearning.order_management.entity.Customer;
import com.subhakar.springbootlearning.order_management.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public GetCustomerResponse getCustomer(UUID id){
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Customer not found"));

        return new GetCustomerResponse(customer.getId(),customer.getName(), customer.getEmail());
    }

    public CreateCustomerResponse createCustomer(CreateCustomerRequest request){
        Customer customer = new Customer(request.getName(), request.getEmail());
        Customer savedCustomer = customerRepository.save(customer);

        return new CreateCustomerResponse(
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getEmail()
        );
    }

    public GetCustomerResponse updateCustomer(UUID id, UpdateCustomerRequest request){

        Customer existingCustomer = customerRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Customer Not Found"));

        existingCustomer.setName(request.getName());
        existingCustomer.setEmail(request.getEmail());

        Customer savedcustomer =  customerRepository.save(existingCustomer);
        return new GetCustomerResponse(
                savedcustomer.getId(),savedcustomer.getName(), savedcustomer.getEmail()
        );
    }

    public void deleteCustomer(UUID id){

        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Customer Not Found"));

        customerRepository.delete(customer);
    }

}