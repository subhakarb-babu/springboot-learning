package com.subhakar.springbootlearning.order_management.exceptions.product;

public class ProductOutOfStockException extends RuntimeException{
    public ProductOutOfStockException(String message){
        super(message);
    }
}