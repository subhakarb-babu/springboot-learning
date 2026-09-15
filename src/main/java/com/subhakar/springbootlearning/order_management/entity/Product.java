package com.subhakar.springbootlearning.order_management.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product{

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column (name = "price")
    private BigDecimal price;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    public Product(){

    }

    public Product(String name, BigDecimal price, Integer stockQuantity){
        this.name = name;
        this.price=price;
        this.stockQuantity=stockQuantity;
    }

    public UUID getId(){return this.id;}
    public String getName(){return this.name;}
    public BigDecimal getPrice(){return this.price;}
    public Integer getStockQuantity(){return this.stockQuantity;}

    public void setName(String name){
        this.name=name;
    }
    public void setPrice(BigDecimal price){
        this.price=price;
    }
    public void setStockQuantity(Integer stockQuantity){
        this.stockQuantity=stockQuantity;
    }

}