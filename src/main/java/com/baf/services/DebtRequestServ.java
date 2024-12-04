package com.baf.services;

import java.util.List;

import com.baf.data.entities.DebtRequest;
import com.baf.data.repositories.DebtRequestList;

public class DebtRequestServ {
    private DebtRequestList debtRequestList;

    public DebtRequestServ(DebtRequestList debtRequestList) {
        this.debtRequestList = debtRequestList;
    }

    public void addDebtRequest() {
        debtRequestList.setDebtRequest(null);
    }

    public List<DebtRequest> getDebtRequests() {
        return debtRequestList.getDebtRequests();
    }
}
