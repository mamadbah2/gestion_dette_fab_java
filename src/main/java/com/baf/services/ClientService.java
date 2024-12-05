package com.baf.services;

import com.baf.core.Service;
import com.baf.data.entities.Client;


public interface ClientService extends Service<Client>{
    Client selectByTel(String tel);
    Client selectBySurname(String surname);

    
}