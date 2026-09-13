package com.subhakar.springbootlearning.order_management.controller;

import com.subhakar.springbootlearning.order_management.dto.CreateCustomerRequest;
import com.subhakar.springbootlearning.order_management.dto.CreateCustomerResponse;
import com.subhakar.springbootlearning.order_management.dto.GetCustomerResponse;
import com.subhakar.springbootlearning.order_management.dto.UpdateCustomerRequest;
import com.subhakar.springbootlearning.order_management.service.CustomerService;
import com.subhakar.springbootlearning.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping ("/api/order/customer")
public class CustomerController{

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService, UserService userService){
        this.customerService = customerService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GetCustomerResponse> updateCustomer(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateCustomerRequest request
    ){
        GetCustomerResponse response = customerService.updateCustomer(id,request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCustomerResponse> getCustomer(@PathVariable("id") UUID id){
        GetCustomerResponse response = customerService.getCustomer(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest request){
        CreateCustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}