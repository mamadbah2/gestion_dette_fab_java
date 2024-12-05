package com.baf.views.impl;

import java.util.List;
import java.util.Scanner;

import com.baf.core.View;

public abstract class ViewImpl<T> implements View<T> {
    protected Scanner scanner = new Scanner(System.in);

    @Override
    public void liste(List<T> datas) {
        for (T t : datas) {
            System.out.println(t.toString());
        }
    }
}
