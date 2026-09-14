package com.subhakar.springbootlearning.order_management.exceptions.customer;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message){
        super(message);
    }
}