package com.baf.data.entities;

import java.util.List;

import lombok.Data;

@Data
public class Debt {
    private int idDebt;
    private Double mount; 
    private String date;
    private Double paidMount; 
    private Double remainingMount;
    private boolean isAchieved;
    private Client client;
    private List<Article> articles;
    private List<Payment> payments;
}
