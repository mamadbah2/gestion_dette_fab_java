package com.baf.data.repositories.list;

import com.baf.data.entities.DetailDebtRequest;
import com.baf.data.repositories.DetailDebtRequestRepository;

public class DetailDebtRequestRepositoryImplList extends RepositoryImplList<DetailDebtRequest> implements DetailDebtRequestRepository {

   

    @Override
    public DetailDebtRequest getDetailDebtById(int idDetailDebt) {
        for (DetailDebtRequest detailDebt : data) {
            if (detailDebt.getId() == idDetailDebt){
                return detailDebt;
            }
        }
        return null;
    }

    
}
