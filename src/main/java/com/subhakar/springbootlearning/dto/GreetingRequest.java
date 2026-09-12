package com.subhakar.springbootlearning.dto;

public record GreetingRequest(
        String name,
        int age
) {
}