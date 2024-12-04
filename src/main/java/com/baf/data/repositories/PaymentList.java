package com.baf.data.repositories;

import java.util.ArrayList;
import java.util.List;

import com.baf.data.entities.Payment;

public class PaymentList {
    private List<Payment> payments = new ArrayList<>();

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayment(Payment payment) {
        payments.add(payment);
    }
}