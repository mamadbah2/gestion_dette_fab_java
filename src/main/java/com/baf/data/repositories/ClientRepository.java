package com.baf.data.repositories;

import com.baf.core.Repository;
import com.baf.data.entities.Client;

public interface ClientRepository extends Repository<Client> {
    Client selectByTel(String tel);
    Client selectBySurname(String surname);
    Client selectById(int id);
}