package com.baf.views.impl;

import java.util.Scanner;

public class AdminMenu { 
    private Scanner scanner;
    public AdminMenu(Scanner scanner ) {

        this.scanner = scanner;
        
    }
    public static void Menu() {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Créer un utilisateur");
            System.out.println("2. Activer/Désactiver un compte utilisateur");
            System.out.println("3. Créer un article");
            System.out.println("4. Lister les articles (filtrer par disponibilité)");
            System.out.println("5. Mettre à jour la quantité d'un article");
            System.out.println("6. Archiver les dettes soldées");
            System.out.println("0. Retour");
        
    }


    public static void boutiquierMenu() {

            System.out.println("\n=== Menu Boutiquier ===");
            System.out.println("1. Créer un client");
            System.out.println("2. Associer un compte utilisateur à un client");
            System.out.println("3. Lister les clients (avec ou sans comptes)");
            System.out.println("4. Rechercher un client par téléphone");
            System.out.println("5. Enregistrer une dette pour un client");
            System.out.println("6. Enregistrer un paiement pour une dette");
            System.out.println("7. Lister les dettes non soldées d’un client");
            System.out.println("8. Gérer les demandes de dette (valider/refuser)");
            System.out.println("0. Retour");
        

    }

    public static void clientMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Menu Client ===");
            System.out.println("1. Lister mes dettes non soldées");
            System.out.println("2. Faire une demande de dette");
            System.out.println("3. Lister mes demandes de dette");
            System.out.println("4. Envoyer une relance pour une demande annulée");
            System.out.println("0. Retour");
    
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> listMyUnsettledDebts();
                case 2 -> makeDebtRequest();
                case 3 -> listMyDebtRequests();
                case 4 -> sendReminderForCancelledRequest();
                case 0 -> {
                    return; // Retour au menu principal
                }
                default -> System.out.println("Choix invalide, essayez encore !");
            }
        }
    }

    

    
}
 
