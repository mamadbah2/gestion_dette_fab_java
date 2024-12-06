package com.baf.views.impl;

import java.util.Scanner;

public class MenuView {
    
    private static Scanner scanner = new Scanner(System.in);

    public static int adminMenu() {

    private static final String RETURN_OPTION = "0. Retour";


    public static void showAdminMenu() {
        System.out.println("\n=== Menu Admin ===");
        System.out.println("1. Créer un compte utilisateur à un client n’ayant pas de compte");
        System.out.println("2. Créer un compte utilisateur avec un rôle Boutiquier ou  Admin");
        System.out.println("3. Désactiver/Activer  un compte utilisateur");
        System.out.println("4. Afficher les comptes utilisateurs  actifs ou par rôle");
        System.out.println("5. Creer un article");
        System.out.println("6. Liste des articles");
        System.out.println("7. Afficher les articles disponibles");
        System.out.println("8. Mettre à jour la quantité en stock d’un article");
        System.out.println("9. Archiver les dettes soldées");
        System.out.println("10. Retour");
        System.out.println("Veillez choisir : ");
        return scanner.nextInt();

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

    public static int showClientMenu() {
        System.out.println("\n=== Menu Client ===");
        System.out.println("1. Lister mes dettes non soldées");
        System.out.println("2. Faire une demande de dette");
        System.out.println("3. Lister mes demandes de dette");
        System.out.println("4. Envoyer une relance pour une demande annulée");
        System.out.println(RETURN_OPTION);

        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextInt();
        }
    }

}
