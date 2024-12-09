package com.baf.data.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DebtRequest {
    int idDebtRequest;
    Date date;
    Client client;
    private List<DetailDebt> detailDebts = new ArrayList<DetailDebt>();
    private double totalAmount;
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    String status;
    int nbre = 0;
    
    public List<DetailDebt> getDetailDebts() {
        return detailDebts;
    }
    public DebtRequest() {
        idDebtRequest = nbre++;
    }
    public int getIdDebtRequest() {
        return idDebtRequest;
    }

    public void addDetailDebt(DetailDebt detailDebt){
        detailDebts.add(detailDebt);
    }
    public void setIdDebtRequest(int idDebtRequest) {
        this.idDebtRequest = idDebtRequest;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public DebtRequest(int idDebtRequest, Date date, Client client, List<Article> articles, String status) {
        this.idDebtRequest = idDebtRequest;
        this.date = date;
        this.client = client;
        this.status = status;
    }
    @Override
    public String toString() {
        return "DebtRequest [idDebtRequest=" + idDebtRequest + ", date=" + date + ", client=" + client.toString()
                + ", detailDebts=" + detailDebts.toString() + ", status=" + status + ", nbre=" + nbre + ", total=" + totalAmount + "]";
    }
  
    
}
