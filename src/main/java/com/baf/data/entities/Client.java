package com.baf.data.entities;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;
@Data
@ToString
public class Client {
    private int id;
    private String surname;
    private String telephone;
    private String adresse;
    private User user;
    private Date createAt = new Date();
    private List<Debt> debts;
    public void addDebt(Debt debt){
        debts.add(debt);
    }
    private User userAccount;

    // private String 
}
