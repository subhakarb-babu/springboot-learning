package com.subhakar.springbootlearning.order_management.exceptions.order;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message){
        super(message);
    }
}