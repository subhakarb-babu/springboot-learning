package com.subhakar.springbootlearning.order_management.dto.product;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductResponse {

    private UUID id;
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;

    public ProductResponse(
            UUID id, String name, BigDecimal price, Integer stockQuantity
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
    public Integer getStockQuantity(){
        return  this.stockQuantity;
    }
}