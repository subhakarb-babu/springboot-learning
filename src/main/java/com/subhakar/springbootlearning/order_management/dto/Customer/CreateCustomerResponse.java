package com.subhakar.springbootlearning.order_management.dto.Customer;

import java.util.UUID;

public class CreateCustomerResponse {

    private UUID id;
    private String name;
    private String email;

    public CreateCustomerResponse(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}