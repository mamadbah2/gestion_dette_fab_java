package com.baf.data.entities;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Debt {
    private int idDebt;
    private Double mount; 
    private Date date = new Date();
    private Double paidMount; 
    private Double remainingMount;
    private boolean isAchieved ;
    private Client client;
    private List<Article> articles;
    private List<Payment> payments;

   public void addArticle(Article article){
       articles.add(article);
   }
   public void addPayment(Payment payment){
       payments.add(payment);
   }
}
