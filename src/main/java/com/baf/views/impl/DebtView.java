package com.baf.views.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baf.data.entities.Article;
import com.baf.data.entities.Client;
import com.baf.data.entities.Debt;
import com.baf.services.ArticleService;
import com.baf.services.ClientService;
import com.baf.services.DebtServ;
import com.baf.services.PaymentServ;

public class DebtView extends ViewImpl<Debt> {
    private Scanner scanner;
    private DebtServ debtServ;
    private ArticleService articleService;
    private ArticleView articleView;
    private PaymentServ paymentServ;
    private ClientService clientService;
    private ClientView clientView;
    public DebtView(Scanner scanner, DebtServ debtServ, ArticleService articleService, ArticleView articleView, PaymentServ paymentServ, ClientService clientService, ClientView clientView) {
        this.scanner = scanner;
        this.debtServ = debtServ;
        this.articleService = articleService;
        this.articleView = articleView;
        this.paymentServ = paymentServ;
        this.clientService = clientService;
        this.clientView = clientView;
    }

    // public void displayAllPaidDebts(Client client) {
    //     List<Debt> debts = debtServ.getAllPaidDebt();
    //     for (Debt debt : debts) {
    //         if (debt.getClient().equals(client)) {
    //             System.out.println(debt.toString());
    //         }
    //     }
    // }



    public void displayAllUnpaidDebts() {
        Client client = clientView.findClientByTel();
        if (client == null) {
            return;
        }
        List<Debt> debts = debtServ.getAllUnpaidDebt(client);
        if (debts.isEmpty()) {
            System.out.println("Aucune dette impayée pour le client ");
            return;
        }
        for (Debt debt : debts) {
            System.out.println(debt.toString());
        }
    }

    public void displayAllDebts() {
        List<Debt> debts = debtServ.selectAll();
        for (Debt debt : debts) {
            System.out.println(debt.toString());
        }
    }

    public void archivePaidDebts() {
        System.out.println("Voici la liste des dettes payees...");
        List<Debt> dettes = debtServ.getAllPaidDebt();
        if (dettes.isEmpty()) {
            System.out.println("Aucune dette n'a ete payee");
            return;
        }
        for (Debt dette : dettes) {
            System.out.println(dette.toString());
        }

        System.out.println("voulez-vous les archiver toutes? (oui/non)");
        String reponse = scanner.nextLine();
        if (reponse.equalsIgnoreCase("oui")) {
            debtServ.archivePaidDebt(dettes);
            System.out.println("Les dettes payees ont ete archives");
        } else if (reponse.equalsIgnoreCase("non")) {
            System.out.println("Entrez l'ID de la dette a archiver");
            int idDebt = scanner.nextInt();
            Debt debt = debtServ.getDebtById(idDebt);
            if (debt != null) {
                debtServ.archivePaidDebt(List.of(debt));
                System.out.println("La dette avec l'ID " + idDebt + " a ete archivée");
            } else {
                System.out.println("Aucune dette avec l'ID " + idDebt + " trouvee");
            }
        } else {
            System.out.println("Reponse invalide");
        }

    }

    public void createDebt() {
        System.out.println("Indentifie toi");
        Client client = clientView.findClientByTel();
        if (client == null) {
            System.out.println("Client non trouvé");
            return;
        }

        System.out.println("Entrez les informations de la dette");
    
        // Entrée pour le montant
        System.out.println("Le montant de la dette");
        double montant = scanner.nextDouble();
        scanner.nextLine(); // Consomme le retour à la ligne
    
        // Entrée pour la date
        // System.out.println("La date de la dette (format YYYY-MM-DD):");
        // String date = scanner.nextLine();
    
        // Affichage des articles disponibles
        System.out.println("Les articles de la dette:");
        List<Article> articles = articleService.selectAll();
        articleView.liste(articles);
    
        List<Article> selectedArticles = new ArrayList<>();
        boolean continuer = true;
    
        while (continuer) {
            System.out.println("Entrez le libellé de l'article:");
            String reponse = scanner.nextLine();
    
            // Recherche de l'article correspondant au libellé
            Article article = articles.stream()
                    .filter(a -> a.getLibelle().equalsIgnoreCase(reponse))
                    .findFirst()
                    .orElse(null);
    
            if (article != null) {
                selectedArticles.add(article);
                System.out.println("Article ajouté : " + article.getLibelle());
            } else {
                System.out.println("Aucun article avec le libellé \"" + reponse + "\" trouvé.");
            }
    
            // Demander si l'utilisateur veut ajouter un autre article
            System.out.println("Voulez-vous ajouter un autre article ? (oui/non):");
            String choix = scanner.nextLine();
            if (!choix.equalsIgnoreCase("oui")) {
                continuer = false;
            }
        }
    
        // Affichage des articles sélectionnés
        System.out.println("Articles sélectionnés :");
        selectedArticles.forEach(article -> System.out.println("- " + article.getLibelle()));
    
        // Exemple de création d'une dette
        Debt debt = new Debt();
        debt.setMount(montant);
        debt.setArticles(selectedArticles);
        debt.setClient(client);
    
        // Sauvegarde de la dette
        debtServ.insert(debt);
        System.out.println("La dette a été créée avec succès !");
    }

    public void showDetteByClient() {
        System.out.println("Veuillez saisir le numero de telephone du client");
        clientView.liste(clientService.selectAll());
        String tel = scanner.nextLine();
        Client client = clientService.selectByTel(tel);
        if (client != null) {
            List<Debt> dettes = debtServ.getDebtsFromClient(client);
            for (Debt dette : dettes) {
                System.out.println(dette.toString());
                System.out.println("Voulez-vous afficher les articles? (oui/non)");
                String reponse = scanner.nextLine();
                if (reponse.equalsIgnoreCase("oui")) {
                    dette.getArticles().forEach(a -> System.out.println(a.toString()));
                }
                System.out.println("Voulez-vous afficher les paiements? (oui/non)");
                reponse = scanner.nextLine();
                if (reponse.equalsIgnoreCase("oui")) {
                    dette.getPayments().forEach(p -> System.out.println(p.toString()));
                }
            }
        } else {
            System.out.println("Ce client n'existe pas");
        }
    }

    @Override
    public Debt saisie() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saisie'");
    }
    

}
