package com.baf.views;

import com.baf.data.entities.DebtRequest;
import com.baf.services.DebtRequestServ;

public class DebtRequestView extends ViewImpl<DebtRequest> {

    private DebtRequestServ debtRequestServ;

    public DebtRequestView(DebtRequestServ debtRequestServ) {
        this.debtRequestServ = debtRequestServ;
    }

    @Override
    public DebtRequest saisie() {
        return null;
    }
    
}