package com.baf.services.impl;

import java.util.List;

import com.baf.data.entities.Payment;
import com.baf.data.repositories.list.PaymentList;
import com.baf.services.PaymentServ;

public class PaymentsServImpl implements PaymentServ {
    private PaymentList paymentList;

    public PaymentsServImpl(PaymentList paymentList) {
        this.paymentList = paymentList;
    }

    public void insert(Payment payment) {
        paymentList.setPayment(payment);
    }

    public List<Payment> selectAll() {
        return paymentList.getPayments();
    }
}