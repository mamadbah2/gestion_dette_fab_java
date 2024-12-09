package com.baf.data.repositories;


import com.baf.core.Repository;
import com.baf.data.entities.DetailDebt;

public interface DetailDebtRepository extends Repository<DetailDebt> {
    DetailDebt getDetailDebtById(int idDetailDebt);
}
