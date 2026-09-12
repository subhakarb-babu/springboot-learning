package com.subhakar.springbootlearning.user.dto;

public class CreateUserResponse {

    private Long id;
    private String name;
    private String email;
    private int age;

    public CreateUserResponse(Long id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}