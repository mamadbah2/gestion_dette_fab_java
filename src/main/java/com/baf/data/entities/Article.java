package com.baf.data.entities;
import lombok.Data;

@Data
public class Article {
    private int id;
    private String libelle;
    private int qteStock;
    private int prix;
}
