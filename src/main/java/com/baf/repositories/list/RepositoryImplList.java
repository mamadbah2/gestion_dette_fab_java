package com.baf.repositories.list;

import java.util.ArrayList;
import java.util.List;

import com.baf.repositories.Repository;

public class RepositoryImplList<T> implements Repository<T>{

    protected List<T> data = new ArrayList<>();

    @Override
    public void insert(T data) {
        this.data.add(data);
    }

    @Override
    public List<T> selectAll() {
       return this.data;
    }
    
}
