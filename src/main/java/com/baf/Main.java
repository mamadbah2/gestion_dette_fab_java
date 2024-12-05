package com.baf;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
       int choice;
       do {
            choice = showMainMenu();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    
                    break;
                case 2:
                    default:
                    break;
        }

       } while  (choice != 12);

       
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