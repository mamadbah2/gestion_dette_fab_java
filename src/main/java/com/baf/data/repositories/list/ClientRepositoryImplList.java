package com.baf.data.repositories.list;

import com.baf.data.entities.Client;
import com.baf.data.repositories.ClientRepository;

public class ClientRepositoryImplList extends RepositoryImplList<Client> implements ClientRepository{

    @Override
    public Client selectByTel(String tel) {
        for (Client client : data) {
            if (client.getTelephone() == tel.trim()){
                return client;
            }
        }
        return null;
    }

    @Override
    public Client selectBySurname(String surname) {
        for (Client client : data) {
            if (client.getSurname() == surname.trim()){
                return client;
            }
        }
        return null;
    }

    @Override
    public Client selectById(int id) {
        for (Client client : data) {
            if (client.getId() == id){
                return client;
            }
        }
        return null;
    }
    
}
