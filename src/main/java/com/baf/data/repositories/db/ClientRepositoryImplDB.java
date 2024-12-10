package com.baf.data.repositories.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.baf.core.database.impl.DatabaseImpl;
import com.baf.data.entities.Client;
import com.baf.data.entities.User;
import com.baf.data.repositories.ClientRepository;

public class ClientRepositoryImplDB  extends DatabaseImpl implements ClientRepository {

    @Override
    public void insert(Client data) {
        String req = String.format("Insert into client (surname, telephone, adresse, userId) values ('%s', '%s', '%s', '%s')",
                data.getSurname(), data.getTelephone(), data.getAdresse(), data.getUser());
        try {
            this.initPreparedStatement(req);
            this.ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> selectAll() {
        List<Client> clients = new ArrayList<>();
        String req = "Select * from client";
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                clients.add(this.convertToObject(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return clients;
    }

    @Override
    public Client convertToObject(ResultSet rs) {
        Client client = new Client();
        try {
            client.setId(rs.getInt("id"));
            client.setSurname(rs.getString("surname"));
            client.setTelephone(rs.getString("telephone"));
            client.setAdresse(rs.getString("adresse"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return client;
    }

    @Override
    public Client selectByTel(String tel) {
       String req = String.format("Select * from client where telephone = '%s'", tel);
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
    public Client selectBySurname(String surname) {
        String req = String.format("Select * from client where surname = '%s'", surname);
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
    public Client selectById(int id) {
        String req = String.format("Select * from client where id = %d", id);
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
    public void createAccount(int id, User user) {
        String req = String.format("Update client set userId = %d where id = %d", user.getIdUser(), id);
        try {
            this.initPreparedStatement(req);
            this.ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
