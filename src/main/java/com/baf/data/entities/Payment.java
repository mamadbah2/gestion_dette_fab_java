package com.baf.data.entities;

import java.util.Date;

public class Payment {
    int idPayment;
    Date date;
    int amount;

    public Payment() {

    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Payment(Date date, int amount) {
        this.date = date;
        this.amount = amount;
    }
    
    
}
