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

    public RozorPayService(
            @Value("${razorpay.key.id}") String keyId,
            @Value("${razorpay.key.secret}") String keySecret
    ) throws RazorpayException {
        this.keyId = keyId;
        this.keySecret = keySecret;
        this.client = new RazorpayClient(keyId, keySecret);
    }

    /** ✅ Create order using Invoice and update it */
    public Invoice createOrder(Invoice invoice) throws RazorpayException {
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
        return invoice;
    }

    /** ✅ Create order with manual parameters */
    public Order createOrder(Integer amount, String receipt, Map<String, String> notes) throws RazorpayException {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100); // in paise
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", receipt);
        orderRequest.put("notes", notes);

        return client.orders.create(orderRequest);
    }

    /** ✅ Verify payment and update Invoice */
    public Invoice verifyPayment(String orderId, String paymentId, String signature, Invoice invoice) {
        try {
            String payload = orderId + "|" + paymentId;
            boolean verified = Utils.verifySignature(payload, signature, keySecret);

            if (verified) {
                invoice.setStatus(PaidStatus.PAID);
                invoice.setPaymentId(paymentId);
                invoice.setPaymentMethod(PaymentType.RAZORPAY);
            } else {
                invoice.setStatus(PaidStatus.FAILED);
            }
        } catch (Exception e) {
            invoice.setStatus(PaidStatus.FAILED);
        }
        return invoice;
    }

    public boolean verifyPaymentSignature(String orderId, String paymentId, String signature) {
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
