package com.daverj.account.dto.mapper;

import com.daverj.account.dto.request.AccountRegistryDTO;
import com.daverj.account.dto.response.AccountDTO;
import com.daverj.account.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountDTO toDto(Account account) {

        if (account == null)
            return null;

        return new AccountDTO(account);

    }

    public Account toEntityRegister(AccountRegistryDTO customerRegistry) {

        if (customerRegistry == null)
            return null;

        Account entity = new Account();

        entity.setId(customerRegistry.getId());
        entity.setEmail(customerRegistry.getEmail());
        entity.setStreamingPlan(customerRegistry.getStreamingPlan());

        return entity;

    }

}