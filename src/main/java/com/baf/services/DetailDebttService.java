package com.baf.services;

import com.baf.core.Service;
import com.baf.data.entities.DetailDebt;

public interface DetailDebttService extends Service<DetailDebt> {
    DetailDebt getDetailDebtByDebt(int debtId);
}
