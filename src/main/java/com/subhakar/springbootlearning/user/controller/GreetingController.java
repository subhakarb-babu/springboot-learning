package com.subhakar.springbootlearning.user.controller;

import com.subhakar.springbootlearning.user.dto.GreetingRequest;
import com.subhakar.springbootlearning.user.dto.GreetingResponse;
import com.subhakar.springbootlearning.user.service.GreetingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/greet")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PostMapping
    public GreetingResponse greet(
            @RequestBody GreetingRequest request) {

        return greetingService.greet(request);
    }
}

