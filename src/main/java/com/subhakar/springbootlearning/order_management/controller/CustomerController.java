package com.subhakar.springbootlearning.order_management.controller;

import com.subhakar.springbootlearning.order_management.dto.customer.CreateCustomerRequest;
import com.subhakar.springbootlearning.order_management.dto.customer.CustomerResponse;
import com.subhakar.springbootlearning.order_management.dto.customer.UpdateCustomerRequest;
import com.subhakar.springbootlearning.order_management.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping ("/api/order/customer")
public class CustomerController{

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateCustomerRequest request
    ){
        CustomerResponse response = customerService.updateCustomer(id,request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("id") UUID id){
        CustomerResponse response = customerService.getCustomerRes(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest request){
        CustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") UUID id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }



}