package com.subhakar.springbootlearning.order_management.repository;

import com.subhakar.springbootlearning.order_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product , UUID> {
    Optional<Product> findByName(String name);
}