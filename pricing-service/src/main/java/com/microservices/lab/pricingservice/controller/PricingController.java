package com.microservices.lab.pricingservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/prices")
public class PricingController {

    private final Random random = new Random();

    @GetMapping("/{bookId}")
    public ResponseEntity<Map<String, Object>> getPrice(
            @PathVariable Long bookId,
            @RequestParam(defaultValue = "false") boolean fail) {

        // Simulate instability: 30% chance of throwing exception
        if (random.nextDouble() < 0.3) {
            throw new IllegalStateException("Random failure: Service instability");
        }

        // Explicit failure simulation
        if (fail) {
            throw new IllegalStateException("Explicit failure requested");
        }

        // Calculate price: 50 + bookId * 5
        double price = 50.0 + bookId * 5.0;

        return ResponseEntity.ok(Map.of(
                "bookId", bookId,
                "price", price
        ));
    }
}

