package com.server.services.queries;

 

import java.util.List;

import com.server.entities.AccountQueryEntity;

public interface AccountQueryService {
    public List<Object> listEventsForAccount(String accountNumber);
    public AccountQueryEntity getAccount(String accountNumber);
}
