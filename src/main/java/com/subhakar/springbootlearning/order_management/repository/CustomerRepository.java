package com.subhakar.springbootlearning.order_management.repository;

import com.subhakar.springbootlearning.order_management.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByName(String name);
}
