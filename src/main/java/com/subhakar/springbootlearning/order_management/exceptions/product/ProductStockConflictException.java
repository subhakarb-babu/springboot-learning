package com.subhakar.springbootlearning.order_management.exceptions.product;

public class ProductStockConflictException extends RuntimeException {

    public ProductStockConflictException(String message) {
        super(message);
    }
}