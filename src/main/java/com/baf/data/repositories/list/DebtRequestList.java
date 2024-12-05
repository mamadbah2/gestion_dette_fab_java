package com.baf.data.repositories.list;

import java.util.ArrayList;
import java.util.List;

import com.baf.data.entities.Client;
import com.baf.data.entities.DebtRequest;
import com.baf.data.repositories.DebtRequestRepository;

public class DebtRequestList implements DebtRequestRepository {
    private List<DebtRequest> debtRequests = new ArrayList<>();

    @Override
    public List<DebtRequest> selectAll() {
        return debtRequests;
    }
    @Override
    public void insert(DebtRequest debtRequest) {
        debtRequests.add(debtRequest);
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
