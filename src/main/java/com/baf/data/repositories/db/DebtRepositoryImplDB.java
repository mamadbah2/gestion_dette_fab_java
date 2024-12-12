package com.baf.data.repositories.db;

import java.sql.ResultSet;
import java.sql.SQLException;
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
                "Insert into debt (mount, date, paidMount, remainingMount, isAchieved, client_id) values ('%s', '%s', '%s', '%s', '%s', '%d')",
                data.getMount(), data.getDate(), data.getPaidMount(), data.getRemainingMount(), data.isAchieved(),
                data.getClient().getId());
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
        String req = "SELECT " +
                "d.id AS debt_id, d.mount AS debt_mount, d.date AS debt_date, " +
                "d.paid_mount AS debt_paidMount, d.remaining_mount AS debt_remainingMount, " +
                "d.is_achieved AS debt_isAchieved, " +
                "c.id AS client_id, c.surname AS client_surname, c.telephone AS client_telephone, " +
                "c.adresse AS client_adresse, c.create_at AS client_createAt " +
                "FROM debt d " +
                "LEFT JOIN client c ON d.client_id = c.id";

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

            // Mapping du client
            int clientId = rs.getInt("client_id");
            if (clientId != 0) {
                Client client = new Client();
                client.setId(clientId);
                client.setSurname(rs.getString("client_surname"));
                client.setTelephone(rs.getString("client_telephone"));
                client.setAdresse(rs.getString("client_adresse"));
                client.setCreateAt(rs.getTimestamp("client_createAt"));
                debt.setClient(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return debt;
    }

    @Override
    public List<Debt> getAllPaidDebt() {
        List<Debt> list = new ArrayList<>();
        String req = "SELECT " +
                "d.id AS debt_id, d.mount AS debt_mount, d.date AS debt_date, " +
                "d.paid_mount AS debt_paidMount, d.remaining_mount AS debt_remainingMount, " +
                "d.is_achieved AS debt_isAchieved, " +
                "c.id AS client_id, c.surname AS client_surname, c.telephone AS client_telephone, " +
                "c.adresse AS client_adresse, c.create_at AS client_createAt " +
                "FROM debt d " +
                "LEFT JOIN client c ON d.client_id = c.id"
                + "where isAchieved = true";
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
        String req = String.format("SELECT " +
                "d.id AS debt_id, d.mount AS debt_mount, d.date AS debt_date, " +
                "d.paid_mount AS debt_paidMount, d.remaining_mount AS debt_remainingMount, " +
                "d.is_achieved AS debt_isAchieved, " +
                "c.id AS client_id, c.surname AS client_surname, c.telephone AS client_telephone, " +
                "c.adresse AS client_adresse, c.create_at AS client_createAt " +
                "FROM debt d " +
                "LEFT JOIN client c ON d.client_id = c.id" +
                " where isAchieved = false and client_id = '%d'" , client.getId());
        
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
        String req = String.format("SELECT " +
                "d.id AS debt_id, d.mount AS debt_mount, d.date AS debt_date, " +
                "d.paid_mount AS debt_paidMount, d.remaining_mount AS debt_remainingMount, " +
                "d.is_achieved AS debt_isAchieved, " +
                "c.id AS client_id, c.surname AS client_surname, c.telephone AS client_telephone, " +
                "c.adresse AS client_adresse, c.create_at AS client_createAt " +
                "FROM debt d " +
                "LEFT JOIN client c ON d.client_id = c.id" +
                "WHERE isAchieved = false and idDebt = %d" , id);
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
