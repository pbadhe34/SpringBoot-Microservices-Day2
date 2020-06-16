package com.server.entities.repositories;

 import org.springframework.data.repository.CrudRepository;

import com.server.entities.AccountQueryEntity;

public interface AccountRepository extends CrudRepository<AccountQueryEntity, String> {
}
