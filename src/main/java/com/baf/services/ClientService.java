package com.baf.services;

import com.baf.core.Service;
import com.baf.entity.Client;


public interface ClientService extends Service<Client>{
    Client selectByTel(String tel);
    Client selectBySurname(String surname);

    
}