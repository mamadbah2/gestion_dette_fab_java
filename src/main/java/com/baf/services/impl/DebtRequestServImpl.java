package com.baf.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.baf.data.entities.Client;
import com.baf.data.entities.DebtRequest;
import com.baf.data.repositories.DebtRequestRepository;
import com.baf.services.DebtRequestServ;

public class DebtRequestServImpl implements DebtRequestServ {
    private DebtRequestRepository debtRequestRepo;

    public DebtRequestServImpl(DebtRequestRepository debtRequestRepo) {
        this.debtRequestRepo = debtRequestRepo;
    }

    public void insert(DebtRequest debtRequest) {
        debtRequestRepo.insert(debtRequest);
    }

    public List<DebtRequest> selectAll() {
        return debtRequestRepo.selectAll();
    }

    public List<DebtRequest> selectPendingRequestsForCl(Client client) {
        List<DebtRequest> pendingRequests = new ArrayList<>();
        for (DebtRequest debtRequest : debtRequestRepo.selectAll()) {
            if (debtRequest.getStatus().equals("en cours") && debtRequest.getClient().equals(client)) {
                pendingRequests.add(debtRequest);
            }
        }
        return pendingRequests;
    }

    public List<DebtRequest> selectCanceledRequestsForCl(Client client) {
        List<DebtRequest> canceledRequests = new ArrayList<>();
        for (DebtRequest debtRequest : debtRequestRepo.selectAll()) {
            if (debtRequest.getStatus().equals("annuler") && debtRequest.getClient().equals(client)) {
                canceledRequests.add(debtRequest);
            }
        }
        return canceledRequests;
    }
}
