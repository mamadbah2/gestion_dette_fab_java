package com.baf.data.repositories.interfaces;

import java.util.List;

import com.baf.data.entities.Client;
import com.baf.data.entities.Debt;

public interface DebtInterf {
    public void createDebt(Debt debt);
    public List<Debt> getAll();
    public List<Debt> getAllPaidDebt();
    public List<Debt> getAllUnpaidDebt();
    public void archivePaidDebt(List<Debt> debts);
    public List<Debt> getDebtsFromClient( Client client);
    public Debt getDebtById(int id);
}