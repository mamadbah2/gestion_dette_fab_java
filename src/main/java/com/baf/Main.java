package com.baf;

import java.util.Scanner;

import com.baf.data.repositories.DebtRepository;
import com.baf.data.repositories.list.DebtList;
import com.baf.services.DebtServ;
import com.baf.services.impl.DebtServImpl;
import com.baf.views.impl.DebtView;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DebtRepository detteRepo = new DebtList();
        // ----------------------------------------------------------------------
        DebtServImpl detteServ = new DebtServImpl(detteRepo);
        // ----------------------------------------------------------------------
        DebtView view = new DebtView(scanner, detteServ);
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
                    int choiceClient = scanner.nextInt();
                    scanner.nextLine();
                    break;
                    do {

                        switch (choiceClient) {
                            case 1:
                                
                                break;

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

    public static void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Bienvenue dans l'application de gestion ===");
        System.out.println("Veuillez choisir un rôle :");
        System.out.println("1. Admin");
        System.out.println("2. Boutiquier");
        System.out.println("3. Client");
        System.out.println("0. Quitter");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> adminMenu();
            case 2 -> boutiquierMenu();
            case 3 -> clientMenu();
            case 0 -> System.exit(0);
            default -> System.out.println("Choix invalide, essayez encore !");
        }
    }

}