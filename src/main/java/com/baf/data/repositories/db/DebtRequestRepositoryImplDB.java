package com.baf.data.repositories.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.baf.core.database.impl.DatabaseImpl;
import com.baf.data.entities.Client;
import com.baf.data.entities.DebtRequest;
import com.baf.data.repositories.DebtRequestRepository;

public class DebtRequestRepositoryImplDB extends DatabaseImpl implements DebtRequestRepository{

    @Override
    public void insert(DebtRequest data) {
       String req = String.format("Insert into debtRequest (totalMount, date, clientId) values ('%s', '%s', '%s', '%s')",
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
        String req = "Select * from debtRequest";
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
            debtRequest.setTotalAmount(rs.getDouble("totalAmount"));
            debtRequest.setDate(rs.getDate("date"));
            debtRequest.setStatus(rs.getString("status"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return debtRequest;
    }

    @Override
    public DebtRequest getDebtRequestByClient(Client client) {
        String req = String.format("Select * from debtRequest where clientId = '%s'", client);
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
        String req = String.format("Update debtRequest set status = '%s' where idDebtRequest = '%s'", status, idDebtRequest);
        try {
            this.initPreparedStatement(req);
            this.ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public DebtRequest getDebtRequestById(int idDebtRequest) {
        String req = String.format("Select * from debtRequest where idDebtRequest = '%s'", idDebtRequest);
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
