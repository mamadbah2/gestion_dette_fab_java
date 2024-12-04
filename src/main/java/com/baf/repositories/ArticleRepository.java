package com.baf.repositories;

import com.baf.entity.Article;

public interface ArticleRepository  extends Repository<Article>{
    Article selectByLibelle(String libelle);
}
