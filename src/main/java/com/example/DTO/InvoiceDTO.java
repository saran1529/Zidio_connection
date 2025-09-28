package com.example.DTO;
public class InvoiceDTO {
    private Long id;
    private String invoiceNumber;
    private Long paymentId;
    private Double amount;
    public InvoiceDTO() {}

    public Long getId() {
        return id; }
    public void setId(Long id) {
        this.id = id; }

    public String getInvoiceNumber() {
        return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber; }

    public Long getPaymentId() {
        return paymentId; }
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId; }

    public Double getAmount() {
        return amount; }
    public void setAmount(Double amount) {
        this.amount = amount; }
}
