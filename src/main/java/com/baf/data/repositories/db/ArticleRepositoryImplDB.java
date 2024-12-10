package com.baf.data.repositories.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baf.core.database.impl.DatabaseImpl;
import com.baf.data.entities.Article;
import com.baf.data.repositories.ArticleRepository;

public class ArticleRepositoryImplDB extends DatabaseImpl implements ArticleRepository {

    

    @Override
    public Article selectById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    @Override
    public void insert(Article article) {
        String req = String.format(
                "Insert into article ( libelle, prix, qteStock) values ('%s', %d, %d)",
                 article.getLibelle(), article.getPrix(), article.getQteStock());
        try {
            this.initPreparedStatement(req);
            this.ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Article> selectAll() {
        List<Article> articles = new ArrayList<>();
        String req = "Select * from article";
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                articles.add(this.convertToObject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


        return articles;
    }

    @Override
    public Article convertToObject(ResultSet rs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToObject'");
    }

    @Override
    public Article selectByLibelle(String libelle) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectByLibelle'");
    }
    
}
