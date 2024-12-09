package com.baf.data.repositories.list;

import com.baf.data.entities.DetailDebt;
import com.baf.data.repositories.DetailDebtRepository;

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
