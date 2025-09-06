package com.example.controller;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/billing")
public class BillingController {
    // Add endpoints that call BillingService for invoice generation, subscription charges
    @GetMapping("/ping") public String ping() { return "billing ok"; }
}
