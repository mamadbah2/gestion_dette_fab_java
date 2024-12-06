package com.baf.views.impl;

import java.util.List;

import com.baf.data.entities.Client;
import com.baf.services.ClientService;

public class ClientView extends ViewImpl<Client>{

      private ClientService clientServices;

    public ClientView (ClientService clientService ) {
        this.clientServices = clientService;
    }

    @Override
    public Client saisie() {
        Client client = new  Client();
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
        return client;
    }

    // Lister les clients n'ayant pas de compte
    public String showClientWhitoutAccount() {
        List<Client> allClients = clientServices.selectAll();
        if (allClients.isEmpty()) {
            System.out.println("Aucun client n'est enregistre");
            return null;
        }
        for (int i = 0; i < allClients.size(); i++) {
            if (allClients.get(i).getUserAccount() != null) {
                System.out.println(i + " - " + allClients.get(i).toString());
            }
        }
        System.out.print("Votre choix : ");
        return scanner.nextLine();
    }
    
}
