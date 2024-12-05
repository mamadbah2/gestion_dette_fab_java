package com.baf.views.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baf.core.enums.Role;
import com.baf.data.entities.User;
import com.baf.services.impl.UserServImpl;

public class UserView {
    private Scanner scanner;
    private UserServImpl userServ;
    private List<User> users = new ArrayList<>();

    public UserView(Scanner scanner, UserServImpl userServ) {
        this.scanner = scanner;
        this.userServ = userServ;
    }

    public User createUser() {
        System.out.println("Creer un user");

        System.out.println("Entrer l'email");
        String email = scanner.nextLine();
        
        System.out.println("Entrer le login");
        String login = scanner.nextLine();
        while (isLoginTaken(login)) {
            System.out.println("Ce login est déjà pris. Veuillez en choisir un autre.");
            login = scanner.nextLine();
        }

        System.out.println("Entrer le mot de passe");
        String password = scanner.nextLine();

        System.out.println("Donner le role. Les roles disponibles: ADMIN, CLIENT, BOUTIQUIER");
        String roleInput = scanner.nextLine().toUpperCase();
        Role role;
        while (true) {
            switch (roleInput) {
                case "ADMIN":
                    role = Role.ADMIN;
                    break;
                case "BOUTIQUIER":
                    role = Role.BOUTIQUIER;
                    break;
                case "CLIENT":
                    role = Role.CLIENT;
                    break;
                default:
                    System.out.println("Role inconnu. Veuillez entrer un role valide.");
                    roleInput = scanner.nextLine().toUpperCase();
                    continue;
            }
            break;
        }

        User user = new User();
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        users.add(user);
        System.out.println("Utilisateur créé avec succès !");
        return user;
        
    }

    private boolean isLoginTaken(String login) {
        for (User user : users) {
            if (user.getLogin().equalsIgnoreCase(login)) {
                return true;
            }
        }
        return false;
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
