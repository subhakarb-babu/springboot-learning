package com.subhakar.springbootlearning.order_management.controller;

import com.subhakar.springbootlearning.order_management.dto.product.CreateProductRequest;
import com.subhakar.springbootlearning.order_management.dto.product.CreateProductResponse;
import com.subhakar.springbootlearning.order_management.dto.product.GetProductResponse;
import com.subhakar.springbootlearning.order_management.dto.product.UpdateProductRequest;
import com.subhakar.springbootlearning.order_management.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController{

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProductResponse> getProduct(
            @PathVariable("id") UUID id
    ){
        GetProductResponse response = productService.getProduct(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(
            @Valid @RequestBody CreateProductRequest request
            ){
        CreateProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GetProductResponse> updateProduct(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateProductRequest request
            ){
        GetProductResponse response = productService.updateProduct(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") UUID id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }



}