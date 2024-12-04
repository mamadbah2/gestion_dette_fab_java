package com.baf.repositories.list;

import com.baf.entity.Article;
import com.baf.repositories.ArticleRepository;

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
