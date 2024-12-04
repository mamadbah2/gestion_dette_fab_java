package com.baf.repositories;


import com.baf.entity.Client;

public interface ClientRepository extends Repository<Client> {
    Client selectByTel(String tel);
    Client selectBySurname(String surname);
    Client selectById(int id);
}