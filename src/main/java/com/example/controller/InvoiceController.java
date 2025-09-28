package com.example.controller;

import com.example.entity.Invoice;
import com.example.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // ---------------- Create invoice for a specific user ----------------
    @PostMapping
    public ResponseEntity<Object> createInvoice(@RequestBody Invoice invoice) {
        try {
            Invoice savedInvoice = invoiceService.createOrderAndAttachToInvoice(invoice);
            return ResponseEntity.ok(savedInvoice);
        } catch (Exception e) {
            e.printStackTrace(); // log in console
            return ResponseEntity.status(500).body(
                    Map.of("error", "Internal Server Error", "message", e.getMessage())
            );
        }
    }

    // Get invoice by ID
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all invoices by user email
    @GetMapping("/user/{email}")
    public ResponseEntity<List<Invoice>> getInvoicesByUserEmail(@PathVariable String email) {
        return ResponseEntity.ok(invoiceService.getInvoicesByUserEmail(email));
    }

    // Get all invoices
    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    // Update invoice
    @PutMapping("/{id}/user/{email}")
    public ResponseEntity<Invoice> updateInvoice(
            @PathVariable Long id,
            @PathVariable String email,
            @RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.updateInvoice(id, email, invoice));
    }

    // Delete invoice
    @DeleteMapping("/{id}/user/{email}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id, @PathVariable String email) {
        invoiceService.deleteInvoice(id, email);
        return ResponseEntity.noContent().build();
    }
}
