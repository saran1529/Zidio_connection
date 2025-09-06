package com.example.DTO;

import java.util.Map;

public class CreateRazorpayOrderRequest {
    private Integer amount;        // amount in paise (e.g. ₹500 = 50000)
    private String receipt;        // receipt number/string
    private String userEmail;      // user’s email
    private Long invoiceId;        // optional: link to invoice
    private Map<String, String> notes; // optional: extra notes

    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getReceipt() {
        return receipt;
    }
    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Map<String, String> getNotes() {
        return notes;
    }
    public void setNotes(Map<String, String> notes) {
        this.notes = notes;
    }
}
