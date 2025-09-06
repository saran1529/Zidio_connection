package com.example.controller;

import com.example.entity.Invoice;
import com.example.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/user/{email}")
    public ResponseEntity<Invoice> createInvoice(@PathVariable String email, @RequestBody Invoice invoice) {
        try {
            invoice.setUserEmail(email);
            Invoice savedInvoice = invoiceService.createOrderAndAttachToInvoice(invoice);
            return ResponseEntity.ok(savedInvoice);
        } catch (Exception e) {
            // Handle exception appropriately
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<Invoice>> getInvoicesByUserEmail(@PathVariable String email) {
        return ResponseEntity.ok(invoiceService.getInvoicesByUserEmail(email));
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @PutMapping("/{id}/user/{email}")
    public ResponseEntity<Invoice> updateInvoice(
            @PathVariable Long id,
            @PathVariable String email,
            @RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.updateInvoice(id, email, invoice));
    }

    @DeleteMapping("/{id}/user/{email}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id, @PathVariable String email) {
        invoiceService.deleteInvoice(id, email);
        return ResponseEntity.noContent().build();
    }
}
