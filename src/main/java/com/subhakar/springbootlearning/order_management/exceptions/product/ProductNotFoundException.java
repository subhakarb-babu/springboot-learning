package com.subhakar.springbootlearning.order_management.exceptions.product;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message){
        super(message);
    }
}