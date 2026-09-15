package com.subhakar.springbootlearning.order_management.service;

import com.subhakar.springbootlearning.order_management.dto.product.CreateProductRequest;
import com.subhakar.springbootlearning.order_management.dto.product.ProductResponse;
import com.subhakar.springbootlearning.order_management.dto.product.UpdateProductRequest;
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

    public ProductResponse getProduct(UUID id){
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product Not Found"));

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStockQuantity()
        );
    }

    public ProductResponse createProduct(CreateProductRequest request){
        Product product = new Product(
                request.getName(),
                request.getPrice(),
                request.getStockQuantity()
        );

        Product savedProduct=productRepository.save(product);
        return new ProductResponse(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getStockQuantity());
    }

    public ProductResponse updateProduct(UUID id, UpdateProductRequest request){
        Product existingProduct = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product Not Found"));
        if(request.getName() != null){
            existingProduct.setName(request.getName());
        }
        if(request.getPrice() != null){
            existingProduct.setPrice(request.getPrice());
        }
        if (request.getStockQuantity() != null) {
            existingProduct.setStockQuantity(request.getStockQuantity());
        }

        Product savedProduct = productRepository.save(existingProduct);
        return new ProductResponse(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getStockQuantity()
        );
    }

    public void deleteProduct(UUID id){
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product Not Found")
        );

        productRepository.delete(product);
    }

}