package com.baf.data.entities;

import java.util.Date;
import java.util.List;

public class DebtRequest {
    int idDebtRequest;
    Date date;
    Client client;
    List<Article> articles;
    String status;
    int nbre = 0;

    public DebtRequest() {
        idDebtRequest = nbre++;
    }
    public int getIdDebtRequest() {
        return idDebtRequest;
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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
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
        this.articles = articles;
        this.status = status;
    }
}
