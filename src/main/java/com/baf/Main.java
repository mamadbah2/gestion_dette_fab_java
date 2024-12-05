package com.baf;

import java.util.Scanner;

import com.baf.data.entities.Client;
import com.baf.data.repositories.ClientRepository;
import com.baf.data.repositories.DebtRepository;
import com.baf.data.repositories.list.ClientRepositoryImplList;
import com.baf.data.repositories.list.DebtList;
import com.baf.services.ClientService;
import com.baf.services.DebtServ;
import com.baf.services.impl.ClientServiceImpl;
import com.baf.services.impl.DebtServImpl;
import com.baf.views.impl.ClientView;
import com.baf.views.impl.DebtView;
import com.baf.views.impl.MenuView;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DebtRepository detteRepo = new DebtList();
        ClientRepository clientRepo = new ClientRepositoryImplList();
        // ----------------------------------------------------------------------
        DebtServ detteServ = new DebtServImpl(detteRepo);
        ClientService clientService = new ClientServiceImpl(clientRepo);
        // ----------------------------------------------------------------------
        DebtView view = new DebtView(scanner, detteServ);
        ClientView clientView = new ClientView(clientService);
        // ----------------------------------------------------------------------

        int choice;
        do {
            choice = showMainMenu();
            scanner.nextLine();
            switch (choice) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    int choiceClient = MenuView.showClientMenu();
                    scanner.nextLine();
                    do {
                        switch (choiceClient) {
                            case 1:
                            System.out.println("Entrez votre numero de telephone");
                            String tel = scanner.nextLine();
                            Client client = clientService.selectByTel(tel);
                                if (client != null) {
                                    view.displayAllUnpaidDebts(client);
                                }
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 0:
                                System.out.println("Au revoir !");
                            default:
                                System.out.println("Choix invalide, essayez encore!");
                                break;
                        }

                    } while (choiceClient != 0);
                    break;
                default:
                    break;
            }

        } while (choice != 12);

    }

    public static int showMainMenu() {
        System.out.println("=== Bienvenue dans l'application de gestion ===");
        System.out.println("Veuillez choisir un rôle :");
        System.out.println("1. Admin");
        System.out.println("2. Boutiquier");
        System.out.println("3. Client");
        System.out.println("0. Quitter");
        return scanner.nextInt();
    }

}