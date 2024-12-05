package com.baf.views;

import java.util.Scanner;

import com.baf.data.entities.User;
import com.baf.services.impl.UserServImpl;

public class UserView {
    private Scanner scanner;
    private UserServImpl userServ;

    public UserView(Scanner scanner, UserServImpl userServ) {
        this.scanner = scanner;
        this.userServ = userServ;
    }

    public void createUser() {
        System.out.println("Creer un user");

        System.out.println("Entrer l'email");
        String email = scanner.nextLine();

        System.out.println("Entrer le login");
        String login = scanner.nextLine();

        System.out.println("Entrer le mot de passe");
        String password = scanner.nextLine();

        System.out.println("Donner le role");
        String role = scanner.nextLine();

        User user = new User();
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);

        userServ.insert(user);
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
