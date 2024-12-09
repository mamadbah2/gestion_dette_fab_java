package com.baf.data.repositories.list;

import com.baf.data.entities.DetailDebt;
import com.baf.data.entities.DetailDebtRequest;
import com.baf.data.repositories.DetailDebtRepository;
import com.baf.data.repositories.DetailDebtRequestRepository;

public class DetailDebtRepositoryImplList extends RepositoryImplList<DetailDebt> implements DetailDebtRepository {

   

    @Override
    public DetailDebt getDetailDebtById(int idDetailDebt) {
        for (DetailDebt detailDebt : data) {
            if (detailDebt.getId() == idDetailDebt){
                return detailDebt;
            }
        }
        return null;
    }

    
}
