package com.example.DTO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SubscriptionPlanDTO {
    private Long id;
    private Long userId;
    private String planName;
    private Double price;       // new field
    private Integer duration;   // new field (in days/months)
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;

    private Long remainingDays;

    public SubscriptionPlanDTO() {}

    public SubscriptionPlanDTO(Long id,
                               Long userId,
                               String planName,
                               Double price,
                               Integer duration,
                               LocalDate startDate,
                               LocalDate endDate,
                               boolean active) {
        this.id = id;
        this.userId = userId;
        this.planName = planName;
        this.price = price;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
        this.remainingDays = calculateRemainingDays(); // auto-set
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRemainingDays(Long remainingDays) {
        this.remainingDays = remainingDays;
    }

    public Long getRemainingDays() {
        return calculateRemainingDays();
    }

    private Long calculateRemainingDays() {
        if (endDate == null) return null;
        LocalDate today = LocalDate.now();
        if (today.isAfter(endDate)) {
            return 0L;
        }
        return ChronoUnit.DAYS.between(today, endDate);
    }
}
