package com.baf.views.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baf.data.entities.Article;
import com.baf.data.entities.Client;
import com.baf.data.entities.DebtRequest;
import com.baf.services.ArticleService;

public class DebtRequestView extends ViewImpl<DebtRequest> {

    private ClientView clientView;
    private ArticleView articleView;
    private ArticleService articleService;

    public DebtRequestView(ArticleView articleView, ArticleService articleService, ClientView clientView) {
        this.articleView = articleView;
        this.articleService = articleService;
        this.clientView = clientView;
    }

    public int subMenuFilter() {
        System.out.println("1. Afficher les demandes de dette en cours");
        System.out.println("2. Afficher les demandes annulées");
        System.out.println("0. Retour");
        return scanner.nextInt();
    }

    @Override
    public void liste(List<DebtRequest> detteRequests) {
        if (detteRequests.isEmpty()) {
            System.out.println("Aucune demande de dette trouvée.");
            return;
        }
        detteRequests.forEach(detteRequest -> System.out.println(detteRequest.toString()));
    }

    @Override
    public DebtRequest saisie() {
        System.out.println("Indentifie toi");
        Client client = clientView.findClientByTel();
        if (client == null) {
            System.out.println("Client non trouvé");
            return null;
        }

        System.out.println("Demande de dette");
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

        DebtRequest debtRequest = new DebtRequest();
        debtRequest.setClient(client);
        debtRequest.setArticles(selectedArticles);
        debtRequest.setStatus("en cours");
        debtRequest.setDate(new Date());

        return debtRequest;
    }
    
}