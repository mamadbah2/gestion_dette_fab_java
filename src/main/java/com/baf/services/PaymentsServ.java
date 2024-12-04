package com.baf.services;

import java.util.List;

import com.baf.data.entities.Payment;
import com.baf.data.repositories.PaymentList;

public class PaymentsServ {
    private PaymentList paymentList;

    public PaymentsServ(PaymentList paymentList) {
        this.paymentList = paymentList;
    }

    public void addPayment() {
        paymentList.setPayment(null);
    }

    public List<Payment> getPayments() {
        return paymentList.getPayments();
    }
}