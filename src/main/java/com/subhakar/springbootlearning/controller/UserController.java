package com.subhakar.springbootlearning.controller;

import com.subhakar.springbootlearning.dto.CreateUserRequest;
import com.subhakar.springbootlearning.dto.CreateUserResponse;
import com.subhakar.springbootlearning.dto.GetUserResponse;
import com.subhakar.springbootlearning.dto.UpdateUserRequest;
import com.subhakar.springbootlearning.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable("id") Long id) {
        GetUserResponse response = userService.getUser(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<GetUserResponse> getUserByEmail(@PathVariable("email") String email){
        GetUserResponse response = userService.getUserByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<GetUserResponse> getUserByName(@PathVariable("name") String name){
        GetUserResponse response = userService.getUserByName(name);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser( @Valid @RequestBody CreateUserRequest request){
        CreateUserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GetUserResponse> updateUser(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateUserRequest request
            ){
        GetUserResponse response = userService.updateUser(id,request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}