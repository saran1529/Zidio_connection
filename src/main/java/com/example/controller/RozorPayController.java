package com.example.controller;

import com.example.DTO.VerifyPaymentRequest;
import com.example.entity.Invoice;
import com.example.enums.PaidStatus;
import com.example.enums.PaymentType;
import com.example.service.InvoiceService;
import com.example.service.RozorPayService;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments/razorpay")
public class RozorPayController {

    private final InvoiceService invoiceService;
    private final RozorPayService razorpayService;

    public RozorPayController(InvoiceService invoiceService, RozorPayService razorpayService) {
        this.invoiceService = invoiceService;
        this.razorpayService = razorpayService;
    }

    @PostMapping("/order")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Invoice invoice) throws Exception {
        Invoice updatedInvoice = invoiceService.createOrderAndAttachToInvoice(invoice);

        Map<String, Object> resp = new HashMap<>();
        resp.put("orderId", updatedInvoice.getRazorpayOrderId());
        resp.put("amount", updatedInvoice.getAmount() * 100); // paise
        resp.put("currency", "INR");
        resp.put("keyId", razorpayService.getKeyId());

        return ResponseEntity.ok(resp);
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyPayment(@RequestBody VerifyPaymentRequest req) {
        Invoice invoice = new Invoice();
        invoice.setId(req.getInvoiceId());
        invoice.setUserEmail(req.getUserEmail());

        Invoice updatedInvoice = invoiceService.verifyAndMarkAsPaid(
                req.getRazorpayOrderId(),
                req.getRazorpayPaymentId(),
                req.getRazorpaySignature(),
                invoice
        );

        boolean verified = updatedInvoice.getStatus() == PaidStatus.PAID;

        Map<String, Object> resp = new HashMap<>();
        resp.put("verified", verified);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/webhook")
    public ResponseEntity<Void> handleWebhook(@RequestHeader("X-Razorpay-Signature") String signature,
                                              @RequestBody String payload) {
        boolean ok = razorpayService.verifyWebhookSignature(payload, signature);
        if (!ok) return ResponseEntity.status(400).build();

        JSONObject event = new JSONObject(payload);
        String eventType = event.optString("event", "");

        if ("payment.captured".equals(eventType)) {
            JSONObject entity = event.getJSONObject("payload").getJSONObject("payment").getJSONObject("entity");
            String paymentId = entity.optString("id");
            JSONObject notes = entity.optJSONObject("notes");

            Long invoiceId = null;
            String userEmail = null;
            if (notes != null) {
                if (notes.has("invoiceId")) invoiceId = notes.optLong("invoiceId");
                if (notes.has("userEmail")) userEmail = notes.optString("userEmail");
            }

            if (invoiceId != null) {
                final String finalUserEmail = userEmail; // ✅ make it effectively final
                invoiceService.getInvoiceById(invoiceId).ifPresent(inv -> {
                    if (finalUserEmail == null || finalUserEmail.equals(inv.getUserEmail())) {
                        invoiceService.markAsPaid(inv.getId(), paymentId, PaymentType.RAZORPAY);
                    }
                });
            }

        }

        return ResponseEntity.ok().build();
    }
}
