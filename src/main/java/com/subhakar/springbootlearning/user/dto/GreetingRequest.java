package com.subhakar.springbootlearning.user.dto;

public record GreetingRequest(
        String name,
        int age
) {
}