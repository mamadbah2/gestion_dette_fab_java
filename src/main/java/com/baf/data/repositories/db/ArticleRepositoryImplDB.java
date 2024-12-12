package com.baf.data.repositories.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.baf.core.database.impl.DatabaseImpl;
import com.baf.data.entities.Article;
import com.baf.data.repositories.ArticleRepository;

public class ArticleRepositoryImplDB extends DatabaseImpl implements ArticleRepository {


    @Override
    public Article selectById(int id) {
        String req = String.format("SELECT * FROM Article WHERE id = %d", id);
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            if (rs.next()) {
                return this.convertToObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public void insert(Article article) {
        String req = String.format(
                "INSERT INTO Article (libelle, prix, qte_stock) values ('%s', %d, %d)",
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
        String req = "Select * from Article";
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                articles.add(this.convertToObject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }


        return articles;
    }

    @Override
    public Article convertToObject(ResultSet rs) {
        Article article = new Article();
        try {
            article.setId(rs.getInt("id"));
            article.setLibelle(rs.getString("libelle"));
            article.setPrix(rs.getInt("prix"));
            article.setQteStock(rs.getInt("qte_stock"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return article;
    }

    @Override
    public Article selectByLibelle(String libelle) {
        String req = String.format("Select * from Article where libelle = '%s'", libelle);
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            if (rs.next()) {
                return this.convertToObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    
}
