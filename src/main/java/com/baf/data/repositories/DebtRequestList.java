package com.baf.data.repositories;

import java.util.ArrayList;
import java.util.List;

import com.baf.data.entities.Client;
import com.baf.data.entities.DebtRequest;

public class DebtRequestList {
    private List<DebtRequest> debtRequests = new ArrayList<>();

    public List<DebtRequest> getDebtRequests() {
        return debtRequests;
    }

    public DebtRequest getDebtRequestByClient(Client client) {
        for (DebtRequest debtRequest : debtRequests) {
            if (debtRequest.getClient().equals(client)) {
                return debtRequest;
            }
        }
        return null;
    }
}
