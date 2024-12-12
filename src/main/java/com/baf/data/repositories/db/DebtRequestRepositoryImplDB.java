package com.baf.data.repositories.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.baf.core.database.impl.DatabaseImpl;
import com.baf.data.entities.Client;
import com.baf.data.entities.DebtRequest;
import com.baf.data.repositories.DebtRequestRepository;

public class DebtRequestRepositoryImplDB extends DatabaseImpl implements DebtRequestRepository {

    @Override
    public void insert(DebtRequest data) {
        String req = String.format(
                "Insert into debtRequest (totalMount, date, clientId) values ('%s', '%s', '%s', '%s')",
                data.getTotalAmount(), data.getDate(), data.getStatus(), data.getClient());
        try {
            this.initPreparedStatement(req);
            this.ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DebtRequest> selectAll() {
        List<DebtRequest> list = new ArrayList<>();
        String req = "SELECT dr.idDebtRequest, dr.date, dr.totalAmount, " +
                "c.id AS client_id, c.surname AS client_surname, " +
                "c.telephone AS client_telephone, c.adresse AS client_adresse, c.createAt AS client_createAt " +
                "FROM DebtRequest dr " +
                "JOIN Client c ON dr.client_id = c.id";
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
    public DebtRequest convertToObject(ResultSet rs) {
        DebtRequest debtRequest = new DebtRequest();
        try {
            debtRequest.setIdDebtRequest(rs.getInt("idDebtRequest"));
            debtRequest.setDate(rs.getDate("date"));
            debtRequest.setTotalAmount(rs.getDouble("totalAmount"));

            // Mapping du client
            int clientId = rs.getInt("client_id");
            if (clientId != 0) {
                Client client = new Client();
                client.setId(clientId);
                client.setSurname(rs.getString("client_surname"));
                client.setTelephone(rs.getString("client_telephone"));
                client.setAdresse(rs.getString("client_adresse"));
                client.setCreateAt(rs.getTimestamp("client_createAt"));
                debtRequest.setClient(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return debtRequest;
    }

    @Override
    public DebtRequest getDebtRequestByClient(Client client) {
        String req = String.format("SELECT dr.idDebtRequest, dr.date, dr.totalAmount, " +
                "c.id AS client_id, c.surname AS client_surname, " +
                "c.telephone AS client_telephone, c.adresse AS client_adresse, c.createAt AS client_createAt " +
                "FROM DebtRequest dr " +
                "JOIN Client c ON dr.client_id = c.id" +
                "where clientId = '%d'", client.getId());
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
    public void updateStatus(int idDebtRequest, String status) {
        String req = String.format("Update debtRequest set status = '%s' where idDebtRequest = '%s'", status,
                idDebtRequest);
        try {
            this.initPreparedStatement(req);
            this.ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public DebtRequest selectById(int idDebtRequest) {
        String req = String.format("SELECT dr.idDebtRequest, dr.date, dr.totalAmount, " +
                "c.id AS client_id, c.surname AS client_surname, " +
                "c.telephone AS client_telephone, c.adresse AS client_adresse, c.createAt AS client_createAt " +
                "FROM DebtRequest dr " +
                "JOIN Client c ON dr.client_id = c.id" + 
                " where idDebtRequest = '%s'", idDebtRequest);
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
