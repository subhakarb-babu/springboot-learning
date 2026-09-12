package com.subhakar.springbootlearning.service;

import com.subhakar.springbootlearning.dto.CreateUserRequest;
import com.subhakar.springbootlearning.dto.CreateUserResponse;
import com.subhakar.springbootlearning.dto.GetUserResponse;
import com.subhakar.springbootlearning.dto.UpdateUserRequest;
import com.subhakar.springbootlearning.entity.User;
import com.subhakar.springbootlearning.exceptions.UserNotFoundException;
import com.subhakar.springbootlearning.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public GetUserResponse getUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found"));

        return new GetUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge()
        );
    }

    public GetUserResponse getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found"));

        return new GetUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge()
        );
    }

    public GetUserResponse getUserByName(String name){
        User user = userRepository.findByName(name).orElseThrow(() ->
                new UserNotFoundException("User Not Found"));
        return new GetUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge()
        );
    }

    public CreateUserResponse createUser(CreateUserRequest request) {

        User user = new User(request.getName(), request.getEmail(), request.getAge());
        User savedUser = userRepository.save(user);

        return new CreateUserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getAge()
        );
    }

    public GetUserResponse updateUser(Long id, UpdateUserRequest updatedUser){

        User existingUser = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found"));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());

        User savedUser = userRepository.save(existingUser);
        return new GetUserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getAge()
        );
    }

    public void  deleteUser(Long id){
        User existingUser = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User Not Found"));

        userRepository.delete(existingUser);
    }
}
