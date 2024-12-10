package com.baf.data.repositories.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.baf.core.database.impl.DatabaseImpl;
import com.baf.data.entities.Client;
import com.baf.data.entities.Debt;
import com.baf.data.repositories.DebtRepository;

public class DebtRepositoryImplDB extends DatabaseImpl implements DebtRepository {

    @Override
    public void insert(Debt data) {
        String req = String.format(
                "Insert into debt (mount, date, paidMount, remainingMount, isAchieved) values ('%s', '%s', '%s', '%s', '%s')",
                data.getMount(), data.getDate(), data.getPaidMount(), data.getRemainingMount(), data.isAchieved());
        try {
            this.initPreparedStatement(req);
            this.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Debt> selectAll() {
        List<Debt> list = new ArrayList<>();
        String req = "Select * from debt";
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                list.add(this.convertToObject(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public Debt convertToObject(ResultSet rs) {
        Debt debt = new Debt();
        try {
            debt.setIdDebt(rs.getInt("idDebt"));
            debt.setMount(rs.getDouble("mount"));
            debt.setDate(rs.getDate("date"));
            debt.setPaidMount(rs.getDouble("paidMount"));
            debt.setRemainingMount(rs.getDouble("remainingMount"));
            debt.setAchieved(rs.getBoolean("isAchieved"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return debt;
    }

    @Override
    public List<Debt> getAllPaidDebt() {
        List<Debt> list = new ArrayList<>();
        String req = "Select * from debt where isAchieved = true";
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                list.add(this.convertToObject(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public List<Debt> getAllUnpaidDebt(Client client) {
        List<Debt> list = new ArrayList<>();
        String req = String.format("Select * from debt where isAchieved = false and client_id = %s", client.getId());
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                list.add(this.convertToObject(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public void archivePaidDebt(List<Debt> debts) {
        for (Debt debt : debts) {
            String req = String.format("Update debt set isAchieved = true where idDebt = %s", debt.getIdDebt());
            try {
                this.initPreparedStatement(req);
                this.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Debt> getDebtsFromClient(Client client) {
        List<Debt> list = new ArrayList<>();
        String req = String.format("Select * from debt where client_id = %s", client.getId());
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                list.add(this.convertToObject(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public Debt getDebtById(int id) {
        String req = String.format("Select * from debt where idDebt = %s", id);
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
    public void updateDebt(Debt debt) {
        String req = String.format(
                "Update debt set mount = '%s', date = '%s', paidMount = '%s', remainingMount = '%s', isAchieved = '%s' where idDebt = %s",
                debt.getMount(), debt.getDate(), debt.getPaidMount(), debt.getRemainingMount(), debt.isAchieved(),
                debt.getIdDebt());
        try {
            this.initPreparedStatement(req);
            this.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
