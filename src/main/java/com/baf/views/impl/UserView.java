package com.baf.views.impl;

import java.util.Scanner;

import com.baf.core.PasswordHashing;
import com.baf.core.enums.Role;
import com.baf.data.entities.User;
import com.baf.services.UserServ;

public class UserView  extends ViewImpl<User>{
    private Scanner scanner;
    private UserServ userServ;

    public UserView(Scanner scanner, UserServ userServ) {
        this.scanner = scanner;
        this.userServ = userServ;
    }


    @Override
    public User saisie() {
        User user = new User();
        boolean userExist = true;
        do {
            System.out.println("Veuillez saisir le login ");
            user.setLogin(scanner.nextLine());
            userExist = userServ.selectByLogin(user.getLogin()) != null;
            if (userExist) {
                System.out.println("Ooups !! Ce login exist deja");
                System.out.println("veuillez réessayer");
            }
        } while (userExist);
        user.setRole(getRole());
        do {
            System.out.println("Veuillez saisir le password");
            user.setPassword(PasswordHashing.hashPassword(scanner.nextLine()));
        } while (user.getPassword().trim() == "");
        return user;
    }


    public Role getRole() {
        int choice = 0;
        do {
            System.out.println("Veuillez choisir le rôle");
            System.out.println("1-Boutiquier");
            System.out.println("2-Admin");
            choice = scanner.nextInt();
        } while (choice != 1 || choice != 2);
        return Role.values()[choice - 1];
    }


    public void getUserById() {
        System.out.println("Entrer l'id du user");
        int userId = scanner.nextInt();
        userServ.getUserById(userId);
    }

    public void toggleUser() {
        System.out.println("Entrer l'id du user");
        int userId = scanner.nextInt();
        userServ.toggleUser(userId);
    }

}
