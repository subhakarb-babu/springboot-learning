package com.subhakar.springbootlearning.order_management.controller;

import com.subhakar.springbootlearning.order_management.dto.Product.CreateProductRequest;
import com.subhakar.springbootlearning.order_management.dto.Product.CreateProductResponse;
import com.subhakar.springbootlearning.order_management.dto.Product.GetProductResponse;
import com.subhakar.springbootlearning.order_management.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController{

    ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @RequestMapping("/{id}")
    public ResponseEntity<GetProductResponse> getProduct(
            @PathVariable("id") UUID id
    ){
        GetProductResponse response = productService.getProduct(id);
        return ResponseEntity.ok(response);
    }

    @RequestMapping
    public ResponseEntity<CreateProductResponse> createProduct(
            @Valid @RequestBody CreateProductRequest request
            ){
        CreateProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}