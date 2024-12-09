package com.baf.data.entities;

import lombok.Data;
@Data
public class DetailDebt {
    private int id;
    private Article article;
    private DebtRequest debtRequest;
    private int qte;
    private double prix;
    private static int nbre = 0;

    public DetailDebt() {
        id = nbre++;
    }

    public DetailDebt(Article article, int qte, double prix) {
        this.article = article;
        this.qte = qte;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "DetailDebt [id=" + id +  ", article=" + article.toString() + 
                 ", qte=" + qte + ", prix=" + prix + "]";
    }

    
}
