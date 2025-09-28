package com.example.service;
import com.example.entity.SubscriptionPlan;
import com.example.repository.SubscriptionPlanRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubscriptionPlanService {

    private final SubscriptionPlanRepository repo;

    public SubscriptionPlanService(SubscriptionPlanRepository repo) {
        this.repo = repo;
    }
    public SubscriptionPlan create(SubscriptionPlan p) {
        return repo.save(p);
    }
    public List<SubscriptionPlan> list() {
        return repo.findAll();
    }
    public SubscriptionPlan get(Long id) {
        return repo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
}
