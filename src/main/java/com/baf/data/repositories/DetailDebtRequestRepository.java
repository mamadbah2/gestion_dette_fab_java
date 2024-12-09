package com.baf.data.repositories;

import com.baf.core.Repository;
import com.baf.data.entities.DetailDebtRequest;

public interface DetailDebtRequestRepository extends Repository<DetailDebtRequest> {
    DetailDebtRequest getDetailDebtById(int idDetailDebt);
} 
