package com.example.DTO;

import java.util.Map;

public class CreateRazorpayOrderRequest {
    private Integer amount;
    private String receipt;
    private String userEmail;
    private Long invoiceId;
    private Map<String, String> notes;

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
