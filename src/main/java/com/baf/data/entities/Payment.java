package com.baf.data.entities;

public class Payment {
    int idPayment;
    int date;
    int amount;

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Payment(int date, int amount) {
        this.date = date;
        this.amount = amount;
    }
    
    
}
