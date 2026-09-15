package com.subhakar.springbootlearning.order_management.exceptions.order_item;

public class OrderItemNotFoundException extends RuntimeException{
    public OrderItemNotFoundException(String message){
        super(message);
    }
}