package com.example.service;

import com.example.entity.Invoice;
import com.example.enums.PaidStatus;
import com.example.enums.PaymentType;
import com.example.repository.InvoiceRepository;
import com.razorpay.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final RozorPayService razorpayService;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, RozorPayService razorpayService) {
        this.invoiceRepository = invoiceRepository;
        this.razorpayService = razorpayService;
    }

    public Invoice createOrderAndAttachToInvoice(Invoice invoice) throws Exception {
        Invoice saved = invoiceRepository.save(invoice);

        // ✅ Get Razorpay Order
        Order order = razorpayService.createOrder(
                saved.getAmount().intValue(),
                "invoice_" + saved.getId(),
                Map.of("invoiceId", String.valueOf(saved.getId()))
        );


        saved.setRazorpayOrderId(order.get("id"));
        saved.setStatus(PaidStatus.PENDING);

        return invoiceRepository.save(saved);
    }

    /** ✅ Fetch invoice by ID */
    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    /** ✅ Fetch all invoices by user email */
    public List<Invoice> getInvoicesByUserEmail(String email) {
        return invoiceRepository.findByUserEmail(email);
    }

    /** ✅ Fetch all invoices */
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    /** ✅ Update an invoice by ID and user email */
    public Invoice updateInvoice(Long id, String email, Invoice updatedInvoice) {
        Optional<Invoice> optional = invoiceRepository.findById(id);
        if (optional.isEmpty()) {
            return null; // or throw exception
        }

        Invoice existing = optional.get();
        if (!existing.getUserEmail().equals(email)) {
            return null; // or throw exception for unauthorized update
        }

        // Update fields
        existing.setAmount(updatedInvoice.getAmount());
        existing.setStatus(updatedInvoice.getStatus());
        existing.setPaymentMethod(updatedInvoice.getPaymentMethod());
        existing.setInvoiceNumber(updatedInvoice.getInvoiceNumber());
        // update other fields if needed

        return invoiceRepository.save(existing);
    }

    /** ✅ Verify Razorpay payment and update invoice in DB */
    public Invoice verifyAndMarkAsPaid(String orderId, String paymentId, String signature, Invoice invoice) {
        Invoice updatedInvoice = razorpayService.verifyPayment(orderId, paymentId, signature, invoice);

        // Always record payment attempt
        updatedInvoice.setPaymentMethod(PaymentType.RAZORPAY);
        updatedInvoice.setPaymentId(paymentId);

        return invoiceRepository.save(updatedInvoice);
    }

    /** ✅ Force mark as paid (fallback, e.g. webhook/manual) */
    public void markAsPaid(Long invoiceId, String paymentId, PaymentType type) {
        invoiceRepository.findById(invoiceId).ifPresent(inv -> {
            inv.setStatus(PaidStatus.PAID);
            inv.setPaymentId(paymentId);
            inv.setPaymentMethod(type);
            invoiceRepository.save(inv);
        });
    }

    /** ✅ Delete an invoice by ID and user email */
    public void deleteInvoice(Long id, String email) {
        invoiceRepository.findById(id).ifPresent(inv -> {
            if (inv.getUserEmail().equals(email)) {
                invoiceRepository.delete(inv);
            }
        });
    }
}
