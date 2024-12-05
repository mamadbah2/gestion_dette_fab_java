package com.baf.views.impl;

import com.baf.data.entities.Article;
import com.baf.services.ArticleService;

public class ArticleView extends ViewImpl<Article> {
    private ArticleService articleServices;

    public ArticleView(ArticleService articleServices) {
        this.articleServices = articleServices;
    }

    public Article saisie() {
        Article article = new Article();
        boolean articleExist;
        do {
            System.out.println("Veuillez saisir le libelle");
            article.setLibelle(scanner.nextLine());
            articleExist = articleServices.selectByLibelle(article.getLibelle()) != null;
            if (articleExist) {
                System.out.println("Ooups !! Cet article exist deja");
                System.out.println("veuillez réessayer");
            }
        } while ("".equals(article.getLibelle().trim()) || articleExist);

        do {
            System.out.println("Veuillez saisir la quantite en stock");
            article.setQteStock(scanner.nextInt());

        } while (article.getQteStock() < 0);

        do {
            System.out.println("Veuillez saisir le prix");
            article.setPrix(scanner.nextInt());

        } while (article.getPrix() < 0);
        return article;
    }
}
