package com.baf.data.entities;

import com.baf.core.enums.Role;

import lombok.Data;

@Data
public class User {
    private int idUser;
    private String email;
    private String login;
    private String password;
    private Role role;
    private Boolean isActive;
    private int nbre;
    public User () {
        this.idUser = nbre++;
    }
    
}
