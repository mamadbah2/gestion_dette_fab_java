package com.baf.data.repositories.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baf.core.database.impl.DatabaseImpl;
import com.baf.data.entities.Article;
import com.baf.data.entities.Debt;
import com.baf.data.entities.DetailDebt;
import com.baf.data.repositories.DetailDebtRepository;

public class DetailDebtRepositoryImplDB extends DatabaseImpl implements DetailDebtRepository {

    @Override
    public void insert(DetailDebt data) {
        String req = String.format(
                "Insert into detailDebt (qte, prix, idArticle, idDebt) values ('%s', '%s', '%s', '%s')",
                data.getQte(), data.getPrix(), data.getArticle(), data.getDebt());
        try {
            this.initPreparedStatement(req);
            this.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DetailDebt> selectAll() {
        List<DetailDebt> list = new ArrayList<>();
        String req = "Select * from detailDebt";
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                list.add(this.convertToObject(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public DetailDebt convertToObject(ResultSet rs) {
        DetailDebt detailDebt = new DetailDebt();
        try {
            detailDebt.setId(rs.getInt("id"));
            detailDebt.setQte(rs.getInt("qte"));
            detailDebt.setPrix(rs.getDouble("prix"));

            // Mapping de l'article
            int articleId = rs.getInt("article_id");
            if (articleId != 0) {
                Article article = new Article();
                article.setId(articleId);
                article.setLibelle(rs.getString("article_libelle"));
                article.setQteStock(rs.getInt("article_qteStock"));
                article.setPrix(rs.getInt("article_prix"));
                detailDebt.setArticle(article);
            }

            // Mapping de la dette
            int debtId = rs.getInt("debt_id");
            if (debtId != 0) {
                Debt debt = new Debt();
                debt.setIdDebt(debtId);
                debt.setMount(rs.getDouble("debt_mount"));
                debt.setDate(rs.getDate("debt_date"));
                debt.setPaidMount(rs.getDouble("debt_paidMount"));
                debt.setRemainingMount(rs.getDouble("debt_remainingMount"));
                debt.setAchieved(rs.getBoolean("debt_isAchieved"));
                detailDebt.setDebt(debt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detailDebt;
    }

    @Override
    public DetailDebt getDetailDebtById(int idDetailDebt) {
        String req = String.format("Select * from detailDebt where idDetailDebt = %d", idDetailDebt);
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            if (rs.next()) {
                return this.convertToObject(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;

    }

    @Override
    public List<DetailDebt> getAllDetailDebtByArticleId(int idArticle) {
        String req = String.format("Select * from detailDebt where idArticle = %d", idArticle);
        List<DetailDebt> list = new ArrayList<>();
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                list.add(this.convertToObject(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<DetailDebt> selectAllByDebtId(int idDebt) {
        String req = String.format("Select * from detailDebt where idDebt = %d", idDebt);
        List<DetailDebt> list = new ArrayList<>();
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                list.add(this.convertToObject(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
