package com.example.service;
import com.example.entity.Payment;
import com.example.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository repo;
    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
    }
    public Payment create(Payment p) {
        return repo.save(p);
    }
    public List<Payment> list() {
        return repo.findAll();
    }
    public Payment get(Long id) {
        return repo
                .findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
