package com.subhakar.springbootlearning.order_management.service;

import com.subhakar.springbootlearning.order_management.dto.customer.CreateCustomerRequest;
import com.subhakar.springbootlearning.order_management.dto.customer.CustomerResponse;
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

    public CustomerResponse getCustomerRes(UUID id){
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Customer not found"));

        return new CustomerResponse(customer.getId(),customer.getName(), customer.getEmail());
    }

    public Customer getCustomer(UUID id){
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Customer not found"));

        return customer;
    }

    public CustomerResponse createCustomer(CreateCustomerRequest request){
        Customer customer = new Customer(request.getName(), request.getEmail());
        Customer savedCustomer = customerRepository.save(customer);

        return new CustomerResponse(
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getEmail()
        );
    }

    public CustomerResponse updateCustomer(UUID id, UpdateCustomerRequest request) {

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

        return new CustomerResponse(
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