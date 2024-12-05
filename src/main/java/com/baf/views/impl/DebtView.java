package com.baf.views.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baf.data.entities.Article;
import com.baf.data.entities.Client;
import com.baf.data.entities.Debt;
import com.baf.services.ArticleService;
import com.baf.services.DebtServ;

public class DebtView {
    private Scanner scanner;
    private DebtServ debtServ;
    private ArticleService articleService;
    private ArticleView articleView;

    public DebtView(Scanner scanner, DebtServ detteServ) {
        this.scanner = scanner;
        this.debtServ = detteServ;
    }

    public void displayAllPaidDebts(Client client) {
        List<Debt> debts = debtServ.getAllPaidDebt(client);
        for (Debt debt : debts) {
            if (debt.getClient().equals(client)) {
                System.out.println(debt.toString());
            }
        }
    }

    public void displayAllUnpaidDebts(Client client) {
        List<Debt> debts = debtServ.getAllUnpaidDebt(client);
        for (Debt debt : debts) {
            if (debt.getClient().equals(client)) {
                System.out.println(debt.toString());
            }
        }
    }

    public void displayAllDebts() {
        List<Debt> debts = debtServ.selectAll();
        for (Debt debt : debts) {
            System.out.println(debt.toString());
        }
    }

    public void archivePaidDebts(Client client) {
        System.out.println("Voici la liste des dettes payees...");
        List<Debt> dettes = debtServ.getAllPaidDebt(client);
        if (dettes.isEmpty()) {
            System.out.println("Aucune dette n'a ete payee");
        }
        for (Debt dette : dettes) {
            System.out.println(dette.toString());
        }

        System.out.println("voulez-vous les archiver toutes?");
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
        }

    }

    public void createDebt() {
        System.out.println("Entrez les informations de la dette:");
    
        // Entrée pour le montant
        System.out.println("Le montant de la dette:");
        double montant = scanner.nextDouble();
        scanner.nextLine(); // Consomme le retour à la ligne
    
        // Entrée pour la date
        System.out.println("La date de la dette (format YYYY-MM-DD):");
        String date = scanner.nextLine();
    
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
        debt.setDate(date);
        debt.setArticles(selectedArticles);
    
        // Sauvegarde de la dette
        debtServ.insert(debt);
        System.out.println("La dette a été créée avec succès !");
    }
    

}
