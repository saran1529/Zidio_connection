package com.example.DTO;
public class RecruiterDTO {
    private Long id;
    private String companyName;
    private String contactEmail;
    private String phone;
    public RecruiterDTO() {}
    // getters/setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getCompanyName() { return companyName; } public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getContactEmail() { return contactEmail; } public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public String getPhone() { return phone; } public void setPhone(String phone) { this.phone = phone; }
}
