package com.subhakar.springbootlearning.order_management.service;

import com.subhakar.springbootlearning.order_management.dto.product.CreateProductRequest;
import com.subhakar.springbootlearning.order_management.dto.product.CreateProductResponse;
import com.subhakar.springbootlearning.order_management.dto.product.GetProductResponse;
import com.subhakar.springbootlearning.order_management.entity.Product;
import com.subhakar.springbootlearning.order_management.exceptions.product.ProductNotFoundException;
import com.subhakar.springbootlearning.order_management.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService{

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public GetProductResponse getProduct(UUID id){
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product Not Found"));

        return new GetProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStockQuantity()
        );
    }

    public CreateProductResponse createProduct(CreateProductRequest request){
        Product product = new Product(
                request.getName(),
                request.getPrice(),
                request.getStockQuantity()
        );

        Product savedProduct=productRepository.save(product);
        return new CreateProductResponse(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getStockQuantity());
    }

}