package com.baf;

import java.util.Scanner;

import com.baf.data.entities.Article;
import com.baf.data.entities.Client;
import com.baf.data.entities.User;
import com.baf.data.repositories.ArticleRepository;
import com.baf.data.repositories.ClientRepository;
import com.baf.data.repositories.DebtRepository;
import com.baf.data.repositories.DebtRequestRepository;
import com.baf.data.repositories.UserRepository;
import com.baf.data.repositories.list.ArticleRepositoryImplList;
import com.baf.data.repositories.list.ClientRepositoryImplList;
import com.baf.data.repositories.list.DebtList;
import com.baf.data.repositories.list.DebtRequestList;
import com.baf.data.repositories.list.UserList;
import com.baf.services.ArticleService;
import com.baf.services.ClientService;
import com.baf.services.DebtRequestServ;
import com.baf.services.UserServ;
import com.baf.services.impl.ArticleServiceImpl;
import com.baf.services.impl.ClientServiceImpl;
import com.baf.services.impl.DebtRequestServImpl;
import com.baf.services.impl.DebtServImpl;
import com.baf.services.impl.UserServImpl;
import com.baf.views.impl.ArticleView;
import com.baf.views.impl.ClientView;
import com.baf.views.impl.DebtRequestView;
import com.baf.views.impl.DebtView;
import com.baf.views.impl.MenuView;
import com.baf.views.impl.UserView;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DebtRepository detteRepo = new DebtList();
        UserRepository userRepo = new UserList();
        ClientRepository clientRepo = new ClientRepositoryImplList();
        ArticleRepository articleRepo = new ArticleRepositoryImplList();
        DebtRequestRepository detteRequestRepo = new DebtRequestList();
        // ----------------------------------------------------------------------
        DebtServImpl detteServ = new DebtServImpl(detteRepo);
        UserServ userServ = new UserServImpl(userRepo);
        ClientService clientService = new ClientServiceImpl(clientRepo);
        ArticleService articleService = new ArticleServiceImpl(articleRepo);
        DebtRequestServ detteRequestServ = new DebtRequestServImpl(detteRequestRepo);
        // ----------------------------------------------------------------------
        
        ClientView clientView = new ClientView(clientService);
        ArticleView articleView = new ArticleView(articleService);
        UserView userView = new UserView(scanner, userServ, clientView, clientService);
        DebtRequestView detteRequestView = new DebtRequestView(articleView, articleService, clientView);
        DebtView view = new DebtView(scanner, detteServ,  clientView, articleService, articleView);
        // ----------------------------------------------------------------------

        int choice;
        do {
            choice = showMainMenu();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    int choiceAdmin;
                    do {
                        choiceAdmin = MenuView.adminMenu();
                        switch (choiceAdmin) {
                            case 1:
                                // Creer un user account pour un en ayant pas
                                User userWhithAccount = userView.createUserForClient();
                                userServ.insert(userWhithAccount);
                                break;
                            case 2:
                                // Creer un user avec un role boutiquier ou admin
                                // Un user c'est aussi un client !!
                                User user = userView.createUser();
                                userServ.insert(user);
                                break;
                            case 3:
                                // Désactiver/Activer un compte utilisateur
                                userView.toggleUserAccount();
                                break;
                            case 4:
                                // Afficher les comptes utilisateurs actifs ou par rôle
                                int subChoiceFilter = userView.subMenuFilter();
                                switch (subChoiceFilter) {
                                    case 1:
                                        userView.showAllActifUsers();
                                        break;
                                    case 2:
                                        userView.showAllUsersByRole();
                                        break;
                                    default:
                                        System.out.println("Option non existante");
                                        break;
                                }
                                break;
                            case 5:
                                // Creer un article
                                Article article = articleView.saisie();
                                articleService.insert(article);
                                break;
                            case 6:
                                // Lister les articles
                                articleView.liste(articleService.selectAll());
                                break;
                            case 7:
                                // Afficher les articles disponibles
                                articleView.showAvailableArticles();
                                break;
                            case 8:
                                // Mettre à jour la quantité d'un article
                                articleView.updateQteStock();
                                break;
                            case 9:
                                // Archiver les dettes soldées
                                view.archivePaidDebts();
                                break;
                            case 10:
                                // Quitter
                                System.out.println("Au revoir");
                                break;
                            default:
                                System.out.println("Option non existante");
                                break;
                        }
                    } while (choiceAdmin != 10);
                    break;
                case 2:

                    break;
                case 3:
                    // Cases pour le client
                    int choiceClient = MenuView.showClientMenu();
                    do {
                        switch (choiceClient) {
                            case 1:
                                // Lister mes dettes non soldées
                                view.displayAllUnpaidDebts();
                                break;
                            case 2:
                                // Faire une demande de dette
                                detteRequestServ.insert(detteRequestView.saisie());
                                break;
                            case 3:
                                // Lister mes demandes de dette avec option de filtre
                                Client client = clientView.findClientByTel();
                                if (client == null) {
                                    System.out.println("Client non trouvé");
                                    break;
                                }
                                detteRequestView.liste(detteRequestServ.selectAll());
                                int subChoiceFilter = detteRequestView.subMenuFilter();
                                do {
                                    switch (subChoiceFilter) {
                                        case 1:
                                            detteRequestView.liste(detteRequestServ.selectPendingRequestsForCl(client));
                                            break;
                                        case 2:
                                            detteRequestView.liste(detteRequestServ.selectCanceledRequestsForCl(client));
                                            break;
                                        case 0:
                                            System.out.println("Retour");
                                            break;
                                        default:
                                            System.out.println("Option non existante");
                                            break;
                                    }
                                } while (subChoiceFilter != 0);
                                break;
                            case 4:
                                // Envoyer une relance pour une demande annulée
                                System.out.println("Pour faire une relance veillez selectionner l'option 2 Faire une demande de dette");
                                System.out.println("Merci pour votre comprehension");
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