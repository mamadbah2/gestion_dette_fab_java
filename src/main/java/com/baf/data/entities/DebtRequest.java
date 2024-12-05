package com.baf.data.entities;

import java.util.List;

public class DebtRequest {
    int idDebtRequest;
    int date;
    Client client;
    List<Article> articles;
    boolean status;

    public int getIdDebtRequest() {
        return idDebtRequest;
    }

    public void setIdDebtRequest(int idDebtRequest) {
        this.idDebtRequest = idDebtRequest;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public DebtRequest(int idDebtRequest, int date, Client client, List<Article> articles, boolean status) {
        this.idDebtRequest = idDebtRequest;
        this.date = date;
        this.client = client;
        this.articles = articles;
        this.status = status;
    }
}
