package com.example.controller;

import com.example.entity.Invoice;
import com.example.enums.PaidStatus;
import com.example.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final InvoiceService invoiceService;

    public PaymentController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/create-order")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Invoice invoice) throws Exception {
        Invoice updatedInvoice = invoiceService.createOrderAndAttachToInvoice(invoice);

        Map<String, Object> resp = new HashMap<>();
        resp.put("orderId", updatedInvoice.getRazorpayOrderId());
        resp.put("amount", updatedInvoice.getAmount() * 100);
        resp.put("currency", "INR");

        return ResponseEntity.ok(resp);
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyPayment(
            @RequestParam String orderId,
            @RequestParam String paymentId,
            @RequestParam String signature,
            @RequestBody Invoice invoice) {

        Invoice updatedInvoice = invoiceService.verifyAndMarkAsPaid(orderId, paymentId, signature, invoice);
        boolean verified = updatedInvoice.getStatus() == PaidStatus.PAID;

        Map<String, Object> resp = new HashMap<>();
        resp.put("verified", verified);
        return ResponseEntity.ok(resp);
    }
}
