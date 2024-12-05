package com.baf.data.repositories.list;

import com.baf.data.entities.Article;
import com.baf.data.repositories.ArticleRepository;

public class ArticleRepositoryImplList extends RepositoryImplList<Article> implements ArticleRepository {

    @Override
    public Article selectByLibelle(String libelle) {
        for (Article article : data) {
            if (article.getLibelle() == libelle.trim()){
                return article;
            }
        }
        return null;
    }
    
}
