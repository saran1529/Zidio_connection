package com.example.service;

import com.example.entity.Invoice;
import com.example.enums.PaidStatus;
import com.example.enums.PaymentType;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RozorPayService {

    private final RazorpayClient client;
    private final String keyId;
    private final String keySecret;

    private final boolean testMode; //  moved to final field (was @Value before)

    public RozorPayService(
            @Value("${razorpay.key.id}") String keyId,
            @Value("${razorpay.key.secret}") String keySecret,
            @Value("${razorpay.test-mode:true}") boolean testMode // inject testMode here instead of @Value field
    ) throws RazorpayException {
        this.keyId = keyId;
        this.keySecret = keySecret;
        this.testMode = testMode; // assign injected property

        // Initialize client only if not in test mode
        if (!testMode) {
            this.client = new RazorpayClient(keyId, keySecret);
        } else {
            this.client = null; // skip Razorpay
        }
    }

    /** Create order using Invoice and update it */
    public Invoice createOrder(Invoice invoice) throws RazorpayException {
        //  merged both duplicate methods into one
        if (testMode || keyId.startsWith("rzp_test_")) {
            // Mock order in test mode
            invoice.setRazorpayOrderId("TEST_ORDER_" + invoice.getId());
            invoice.setStatus(PaidStatus.PENDING);
            return invoice;
        }

        Map<String, String> notes = new HashMap<>();
        if (invoice.getUserEmail() != null) notes.put("userEmail", invoice.getUserEmail());
        notes.put("invoiceId", String.valueOf(invoice.getId()));

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", invoice.getAmount().intValue() * 100); // in paise
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "invoice_" + invoice.getId());
        orderRequest.put("notes", notes);

        Order order = client.orders.create(orderRequest);

        invoice.setRazorpayOrderId(order.get("id"));
        invoice.setStatus(PaidStatus.PENDING);
        return invoice;
    }

    /** Verify payment and update Invoice */
    public Invoice verifyPayment(String orderId, String paymentId, String signature, Invoice invoice) {
        try {
            if (testMode) {
                // In test mode, mark as paid automatically
                invoice.setStatus(PaidStatus.PAID);
                invoice.setPaymentId(paymentId != null ? paymentId : "TEST_PAYMENT");
                invoice.setPaymentType(PaymentType.RAZORPAY);
                return invoice;
            }

            String payload = orderId + "|" + paymentId;
            boolean verified = Utils.verifySignature(payload, signature, keySecret);

            if (verified) {
                invoice.setStatus(PaidStatus.PAID);
                invoice.setPaymentId(paymentId);
                invoice.setPaymentType(PaymentType.RAZORPAY);
            } else {
                invoice.setStatus(PaidStatus.FAILED);
            }
        } catch (Exception e) {
            invoice.setStatus(PaidStatus.FAILED);
        }
        return invoice;
    }

    public boolean verifyPaymentSignature(String orderId, String paymentId, String signature) {
        if (testMode) return true; // auto-verify in test mode
        try {
            JSONObject options = new JSONObject();
            options.put("razorpay_order_id", orderId);
            options.put("razorpay_payment_id", paymentId);
            options.put("razorpay_signature", signature);

            Utils.verifyPaymentSignature(options, keySecret);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyWebhookSignature(String payload, String signature) {
        if (testMode) return true;
        try {
            Utils.verifyWebhookSignature(payload, signature, keySecret);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getKeyId() {
        return keyId;
    }
}
