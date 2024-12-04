package com.baf.vues;

import java.util.List;

public interface View<T> {
    T saisie();
    void liste(List<T> datas);

}
