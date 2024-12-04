package com.baf.services;

import com.baf.core.Service;
import com.baf.entity.Article;

public interface ArticleService extends Service<Article>  {
    Article selectByLibelle(String libelle);
}
