package com.baf.data.entities;

import lombok.Data;
import java.util.Date;
@Data
public class Client {
    private int id;
    private String surname;
    private String telephone;
    private String adresse;
    private Date createAt;
    private Date updatetAt;
    private User userAccount;

    // private String 
}
