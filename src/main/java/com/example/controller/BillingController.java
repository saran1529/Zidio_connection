package com.example.controller;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/billing")
public class BillingController {
    @GetMapping("/ping") public String ping() {
        return "billing ok"; }
}
