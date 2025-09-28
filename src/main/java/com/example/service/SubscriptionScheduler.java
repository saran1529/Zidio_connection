package com.example.service;

import com.example.entity.UserPaymentStatus;
import com.example.repository.UserPaymentStatusRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubscriptionScheduler {

    private final UserPaymentStatusRepository repository;

    public SubscriptionScheduler(UserPaymentStatusRepository repository) {
        this.repository = repository;
    }

    /** Run every day at midnight (00:00) */
    @Scheduled(cron = "0 0 0 * * ?")
    public void deactivateExpiredPlans() {
        List<UserPaymentStatus> all = repository.findAll();
        LocalDate today = LocalDate.now();

        for (UserPaymentStatus plan : all) {
            if (plan.isActive() && today.isAfter(plan.getEndDate())) {
                plan.setActive(false);
                repository.save(plan);
            }
        }
        System.out.println(" Subscription expiry check completed at: " + today);
    }
}
