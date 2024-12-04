package com.baf.data.entities;

import lombok.Data;

@Data
public class User {
    private int idUser;
    private String email;
    private String login;
    private String password;
    private String role;
    private Boolean isActive;
}
