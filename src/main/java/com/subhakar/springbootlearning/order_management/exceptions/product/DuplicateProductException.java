package com.subhakar.springbootlearning.order_management.exceptions.product;

public class DuplicateProductException extends RuntimeException{
    public DuplicateProductException(String message){
        super(message);
    }
}