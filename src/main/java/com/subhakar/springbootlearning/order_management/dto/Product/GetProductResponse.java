package com.subhakar.springbootlearning.order_management.dto.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class GetProductResponse{

    private UUID id;
    private String name;
    private BigDecimal price;
    private int stockQuantity;

    public GetProductResponse(
            UUID id, String name, BigDecimal price, int stockQuantity
    ){
        this.id=id;
        this.name=name;
        this.price=price;
        this.stockQuantity=stockQuantity;
    }

    public UUID getId(){
        return  this.id;
    }
    public String getName(){
        return  this.name;
    }
    public BigDecimal getPrice(){
        return  this.price;
    }
    public int getStockQuantity(){
        return  this.stockQuantity;
    }
}