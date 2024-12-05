package com.baf.data.repositories.list;

import java.util.ArrayList;
import java.util.List;

import com.baf.data.entities.Payment;
import com.baf.data.repositories.PaymentRepository;

public class PaymentList implements PaymentRepository {
    private List<Payment> payments = new ArrayList<>();

    @Override
    public List<Payment> selectAll() {
        return payments;
    }

    @Override
    public void insert(Payment payment) {
        payments.add(payment);
    }
}