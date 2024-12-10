package com.baf.data.repositories.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.baf.core.database.impl.DatabaseImpl;
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
            detailDebt.setId(rs.getInt("idDetailDebt"));
            detailDebt.setQte(rs.getInt("qte"));
            detailDebt.setPrix(rs.getDouble("prix"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

    
}
