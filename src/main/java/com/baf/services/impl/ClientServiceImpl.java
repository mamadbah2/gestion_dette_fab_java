package com.baf.services.impl;

import java.util.List;

import com.baf.entity.Client;
import com.baf.repositories.ClientRepository;
import com.baf.services.ClientService;

public class ClientServiceImpl  implements ClientService{
    private ClientRepository clientRepository;

    public ClientServiceImpl( ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    
    @Override
    public List<Client> selectAll() {
        return clientRepository.selectAll();
    }

    @Override
    public void insert(Client data) {
      clientRepository.insert(data);
    }


    @Override
    public Client selectByTel(String tel) {
        return clientRepository.selectByTel(tel);
    }


    @Override
    public Client selectBySurname(String surname) {
        return clientRepository.selectBySurname(surname);
    }


  
    
}