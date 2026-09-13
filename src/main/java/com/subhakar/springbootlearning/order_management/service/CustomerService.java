package com.subhakar.springbootlearning.order_management.service;

import com.subhakar.springbootlearning.order_management.dto.CreateCustomerRequest;
import com.subhakar.springbootlearning.order_management.dto.CreateCustomerResponse;
import com.subhakar.springbootlearning.order_management.dto.GetCustomerResponse;
import com.subhakar.springbootlearning.order_management.entity.Customer;
import com.subhakar.springbootlearning.order_management.repository.CustomerRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
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

}