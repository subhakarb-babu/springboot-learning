package com.subhakar.springbootlearning.user.service;

import com.subhakar.springbootlearning.user.dto.GreetingRequest;
import com.subhakar.springbootlearning.user.dto.GreetingResponse;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public GreetingResponse greet(GreetingRequest request) {

        if (request.age() >= 18) {
            return new GreetingResponse(
                    "Hey " + request.name() +
                            ", you are allowed to use this web"
            );
        }

        return new GreetingResponse(
                "Hey " + request.name() +
                        ", you are not allowed to use this web"
        );
    }
}
