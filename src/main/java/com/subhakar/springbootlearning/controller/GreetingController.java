package com.subhakar.springbootlearning.controller;

import com.subhakar.springbootlearning.dto.GreetingRequest;
import com.subhakar.springbootlearning.dto.GreetingResponse;
import com.subhakar.springbootlearning.service.GreetingService;
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

