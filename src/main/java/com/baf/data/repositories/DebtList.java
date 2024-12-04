package com.baf.data.repositories;

import java.util.ArrayList;
import java.util.List;

import com.baf.data.entities.Client;
import com.baf.data.entities.Debt;
import com.baf.data.repositories.interfaces.DebtInterf;

public class DebtList implements DebtInterf {

    private List<Debt> debts = new ArrayList<>();
    @Override
    public List<Debt> getAll() {
        return debts;
    }

    @Override
    public List<Debt> getAllPaidDebt() {
        List<Debt> debtsPaid = new ArrayList<>();
        for (Debt debt : debts) {
            if (debt.getRemainingMount() == 0) {
                debtsPaid.add(debt);
            }
        }
        return debtsPaid;
    }

    @Override
    public List<Debt> getAllUnpaidDebt() {
        List<Debt> debtsUnpaid = new ArrayList<>();
        for (Debt debt : debts) {
            if (debt.getRemainingMount() != 0) {
                debtsUnpaid.add(debt);
            }
        }
        return debtsUnpaid;
    }

    @Override
    public void archivePaidDebt(List<Debt> debts) {
        for (Debt debt : debts) {
            debt.setAchieved(debt.getRemainingMount() == 0);
        }
    }

    @Override
    public void createDebt(Debt debt) {
        debts.add(debt);
    }

    @Override
    public List<Debt> getDebtsFromClient(Client client) {
        List<Debt> debtsFromClient = new ArrayList<>();
        for (Debt debt : debts) {
            if (debt.getClient().equals(client)) {
                debtsFromClient.add(debt);
            }
        }
        return debtsFromClient;
    }

    @Override
    public Debt getDebtById(int id) {
        for (Debt debt : debts) {
            if (debt.getIdDebt() == id) {
                return debt;
            }
        }
        return null;
    }
    
}
