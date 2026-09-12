package com.subhakar.springbootlearning.user.dto;

import jakarta.validation.constraints.NotBlank;

public class GetUserRequest {

    @NotBlank
    private String id;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}