package com.baf.core;

import java.util.List;

public interface Service<T> {
    List<T> selectAll();
    void insert(T data);
}
