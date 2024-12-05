package com.baf.services.impl;

import java.util.List;

import com.baf.data.entities.DebtRequest;
import com.baf.data.repositories.list.DebtRequestList;
import com.baf.services.DebtRequestServ;

public class DebtRequestServImpl implements DebtRequestServ {
    private DebtRequestList debtRequestList;

    public DebtRequestServImpl(DebtRequestList debtRequestList) {
        this.debtRequestList = debtRequestList;
    }

    public void insert(DebtRequest debtRequest) {
        debtRequestList.insert(debtRequest);
    }

    public List<DebtRequest> selectAll() {
        return debtRequestList.selectAll();
    }
}
