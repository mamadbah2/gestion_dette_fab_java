package com.baf.views.impl;

import java.util.ArrayList;
import java.util.List;

import com.baf.data.entities.Client;
import com.baf.data.entities.User;
import com.baf.services.ClientService;

public class ClientView extends ViewImpl<Client> {

    private ClientService clientServices;
    private UserView userView;

    public ClientView(ClientService clientService, UserView userView) {
        this.clientServices = clientService;
        this.userView = userView;
    }

    @Override
    public Client saisie() {
        Client client = new Client();
        do {
            System.out.println("Veuillez saisir le surnom du client ");
            client.setSurname(scanner.nextLine());
        } while (client.getSurname() == "");
        boolean clientExist = true;
        do {
            System.out.println("Veuillez saisir le numero de telephone du client ");
            client.setTelephone(scanner.nextLine());
            clientExist = clientServices.selectByTel(client.getTelephone()) != null;
            if (clientExist) {
                System.out.println("Ooups !! Ce numero exist deja");
                System.out.println("veuillez réessayer");

            }
        } while (clientExist);
        do {
            System.out.println("Veuillez saisir l'adresse du client ");
            client.setAdresse(scanner.nextLine());
        } while (client.getAdresse() == "");

        System.out.println("Voulez-vous ajouter un compte a ce client ? O/N");
        String ok = scanner.nextLine().trim();
        if (ok.toUpperCase().equals("O")) {
            User user = userView.saisie();
            if (user != null) {
                client.setUser(user);
            }
        }
        return client;
    }

    public void filtre( List<Client> clients) {
        int choix = obtenirChoix();

        for (Client client : clients) {
            if (client != null) {
                switch (choix) {
                    case 1:
                        if (client.getUser() != null) {
                            System.out.println(client.toString());
                        }
                        break;
                    case 2:
                        if (client.getUser() == null) {
                            System.out.println(client.toString());
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private int obtenirChoix() {
        System.out.println("Veuillez choisir une option :");
        System.out.println("1- Avec compte");
        System.out.println("2- Sans compte");
        int choix = -1;
        while (choix != 1 && choix != 2) {
            choix = scanner.nextInt();
            if (choix != 1 && choix != 2) {
                System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
        return choix;
    }

    // public int

}
