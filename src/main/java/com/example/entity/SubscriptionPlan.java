package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "subscription_plans")
public class SubscriptionPlan
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Integer durationInDays;


    public SubscriptionPlan() {}

    public SubscriptionPlan(Long id,String name,String description,Double price,Integer durationInDays) {
        this.id=id;
        this.name=name;
        this.description=description;
        this.price=price;
        this.durationInDays=durationInDays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(Integer durationInDays) {
        this.durationInDays = durationInDays;
    }
}
