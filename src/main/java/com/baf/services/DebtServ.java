package com.baf.services;

import java.util.List;

import com.baf.data.entities.Client;
import com.baf.data.entities.Debt;
import com.baf.data.repositories.interfaces.DebtInterf;

public class DebtServ {
    private DebtRepository debtInterf;

    public DebtServ(DebtRepository debtInterf) {
        this.debtInterf = debtInterf;
    }

    public void archivePaidDebt(List<Debt> debts) {
        debtInterf.archivePaidDebt(debts);
    }

    public List<Debt> getAllPaidDebt() {
        return debtInterf.getAllPaidDebt();
    }

    public List<Debt> getAllUnpaidDebt() {
        return debtInterf.getAllUnpaidDebt();
    }

    public List<Debt> getAllDebt() {
        return debtInterf.getAll();
    }

    public void createDebt(Debt debt) {
        debtInterf.createDebt(debt);
    }

    public List<Debt> getDebtsFromClient(Client client) {
        return debtInterf.getDebtsFromClient(client);
    }

    public Debt getDebtById(int id) {
        return debtInterf.getDebtById(id);
    }

}
