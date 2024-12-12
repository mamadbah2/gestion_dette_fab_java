package com.baf.data.repositories.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.baf.core.database.impl.DatabaseImpl;
import com.baf.core.enums.Role;
import com.baf.data.entities.Client;
import com.baf.data.entities.User;
import com.baf.data.repositories.ClientRepository;

public class ClientRepositoryImplDB extends DatabaseImpl implements ClientRepository {

    @Override
    public void insert(Client data) {
        String req = String.format(
                "INSERT INTO Client (surname, telephone, address, user_id) values ('%s', '%s', '%s', '%s')",
                data.getSurname(), data.getTelephone(), data.getAdresse(), data.getUser());
        if (data.getUser() == null) {
            req = String.format("INSERT INTO client (surname, telephone, address) values ('%s', '%s', '%s')",
                    data.getSurname(), data.getTelephone(), data.getAdresse());
        }
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
        String req = "SELECT " +
                "c.id AS client_id, c.surname AS client_surname, c.telephone AS client_telephone, " +
                "c.address AS client_adresse, c.date AS client_createAt, " +
                "u.id AS user_id, u.login AS user_login, u.email AS user_email, u.role AS user_role " +
                "u.is_active AS user_is_active" +
                "FROM Client c " +
                "LEFT JOIN Users u ON c.user_id = u.id";
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
            client.setId(rs.getInt("client_id"));
            client.setSurname(rs.getString("client_surname"));
            client.setTelephone(rs.getString("client_telephone"));
            client.setAdresse(rs.getString("client_adresse"));
            client.setCreateAt(rs.getDate("client_create_at"));
            User user = new User();
            user.setIdUser(rs.getInt("user_userId"));
            user.setLogin(rs.getString("user_login"));
            user.setEmail(rs.getString("user_email"));
            user.setPassword(rs.getString("user_password"));
            user.setIsActive(rs.getBoolean("user_is_active"));
            String str_role = rs.getString("user_role");
            switch (str_role) {
                case "ADMIN":
                    user.setRole(Role.ADMIN);
                    break;
                case "BOUTIQUIER":
                    user.setRole(Role.BOUTIQUIER);
                    break;
                case "CLIENT":
                    user.setRole(Role.CLIENT);
                    break;
            }
            client.setUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return client;
    }

    @Override
    public Client selectByTel(String tel) {
        String req = String.format("SELECT " +
                "c.id AS client_id, c.surname AS client_surname, c.telephone AS client_telephone, " +
                "c.address AS client_adresse, c.date AS client_createAt, " +
                "u.id AS user_id, u.login AS user_login, u.email AS user_email, u.role AS user_role " +
                "u.is_active AS user_is_active" +
                "FROM Client c " +
                "LEFT JOIN Users u ON c.user_id = u.id"
                + " WHERE telephone = '%s'", tel);
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
        String req = String.format("SELECT " +
                "c.id AS client_id, c.surname AS client_surname, c.telephone AS client_telephone, " +
                "c.address AS client_adresse, c.date AS client_createAt, " +
                "u.id AS user_id, u.login AS user_login, u.email AS user_email, u.role AS user_role " +
                "u.is_active AS user_is_active" +
                "FROM Client c " +
                "LEFT JOIN Users u ON c.user_id = u.id"
                + " WHERE surname = '%s'", surname);
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
        String req = String.format("SELECT " +
                "c.id AS client_id, c.surname AS client_surname, c.telephone AS client_telephone, " +
                "c.address AS client_adresse, c.date AS client_createAt, " +
                "u.id AS user_id, u.login AS user_login, u.email AS user_email, u.role AS user_role " +
                "u.is_active AS user_is_active" +
                "FROM Client c " +
                "LEFT JOIN Users u ON c.user_id = u.id"
        + " WHERE c.id = '%d'", id);
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
        String req = String.format("UPDATE Client set user_id = %d WHERE id = %d", user.getIdUser(), id);
        try {
            this.initPreparedStatement(req);
            this.ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
