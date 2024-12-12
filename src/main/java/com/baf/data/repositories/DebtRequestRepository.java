package com.baf.data.repositories;

import com.baf.core.Repository;
import com.baf.data.entities.Client;
import com.baf.data.entities.DebtRequest;

public interface DebtRequestRepository extends Repository<DebtRequest> {
    DebtRequest getDebtRequestByClient(Client client);
    void updateStatus(int idDebtRequest, String status);
    DebtRequest selectById(int idDebtRequest);
}
