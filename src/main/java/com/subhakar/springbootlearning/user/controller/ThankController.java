package com.subhakar.springbootlearning.user.controller;

import com.subhakar.springbootlearning.user.dto.ThankRequest;
import com.subhakar.springbootlearning.user.dto.ThankResponse;
import com.subhakar.springbootlearning.user.service.ThankService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/thank")
public class ThankController {
    private final ThankService thankService;

    public ThankController(ThankService thankService) {
        this.thankService = thankService;
    }

    @PostMapping
    public ThankResponse thank(@RequestBody ThankRequest request) {
        return thankService.thank(request);
    }
}
