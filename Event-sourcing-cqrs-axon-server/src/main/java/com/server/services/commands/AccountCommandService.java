package com.server.services.commands;

 
import java.util.concurrent.CompletableFuture;

import com.server.dto.commands.AccountCreateDTO;
import com.server.dto.commands.MoneyCreditDTO;
import com.server.dto.commands.MoneyDebitDTO;

public interface AccountCommandService {

    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO);
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO);
}
