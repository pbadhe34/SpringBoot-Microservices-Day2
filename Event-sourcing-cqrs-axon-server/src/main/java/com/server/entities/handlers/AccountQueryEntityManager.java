package com.server.entities.handlers;

 

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.server.aggregates.AccountAggregate;
import com.server.entities.AccountQueryEntity;
import com.server.entities.repositories.AccountRepository;
import com.server.events.BaseEvent;

@Component
public class AccountQueryEntityManager {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    @Qualifier("accountAggregateEventSourcingRepository")
    private EventSourcingRepository<com.server.aggregates.AccountAggregate> accountAggregateEventSourcingRepository;

    @EventSourcingHandler
    void on(BaseEvent event){
        persistAccount(buildQueryAccount(getAccountFromEvent(event)));
    }


    private AccountAggregate getAccountFromEvent(BaseEvent event){
        return accountAggregateEventSourcingRepository.load(event.id.toString()).getWrappedAggregate().getAggregateRoot();
    }

    private AccountQueryEntity findExistingOrCreateQueryAccount(String id){
        return accountRepository.findById(id).isPresent() ? accountRepository.findById(id).get() : new AccountQueryEntity();
    }

    private AccountQueryEntity buildQueryAccount(AccountAggregate accountAggregate){
        AccountQueryEntity accountQueryEntity = findExistingOrCreateQueryAccount(accountAggregate.getId());

        accountQueryEntity.setId(accountAggregate.getId());
        accountQueryEntity.setAccountBalance(accountAggregate.getAccountBalance());
        accountQueryEntity.setCurrency(accountAggregate.getCurrency());
        accountQueryEntity.setStatus(accountAggregate.getStatus());

        return accountQueryEntity;
    }

    private void persistAccount(AccountQueryEntity accountQueryEntity){
        accountRepository.save(accountQueryEntity);
    }
}