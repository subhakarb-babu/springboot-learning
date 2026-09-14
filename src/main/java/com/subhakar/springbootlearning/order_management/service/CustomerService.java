package com.subhakar.springbootlearning.order_management.service;

import com.subhakar.springbootlearning.order_management.dto.customer.CreateCustomerRequest;
import com.subhakar.springbootlearning.order_management.dto.customer.CreateCustomerResponse;
import com.subhakar.springbootlearning.order_management.dto.customer.GetCustomerResponse;
import com.subhakar.springbootlearning.order_management.dto.customer.UpdateCustomerRequest;
import com.subhakar.springbootlearning.order_management.entity.Customer;
import com.subhakar.springbootlearning.order_management.exceptions.customer.CustomerNotFoundException;
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
                new CustomerNotFoundException("Customer not found"));

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

    public GetCustomerResponse updateCustomer(UUID id, UpdateCustomerRequest request) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found"));

        if (request.getName() != null) {
            existingCustomer.setName(request.getName());
        }

        if (request.getEmail() != null) {
            existingCustomer.setEmail(request.getEmail());
        }

        Customer savedCustomer = customerRepository.save(existingCustomer);

        return new GetCustomerResponse(
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getEmail()
        );
    }

    public void deleteCustomer(UUID id){

        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Customer Not Found"));

        customerRepository.delete(customer);
    }

}