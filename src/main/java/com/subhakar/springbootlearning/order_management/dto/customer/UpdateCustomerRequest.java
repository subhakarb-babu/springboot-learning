package com.subhakar.springbootlearning.order_management.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateCustomerRequest {

    @NotBlank(message = "Name should not be Empty")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "Email should not be Empty")
    @Email(message = "Email should be Valid")
    @Size(max = 255)
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}