package com.baf.vues;

import java.util.List;
import java.util.Scanner;

public abstract class ViewImpl<T> implements View<T> {
    protected Scanner scanner = new Scanner(System.in);

    @Override
    public void liste(List<T> datas) {
        for (T t : datas) {
            System.out.println(t);
        }
    }
}
