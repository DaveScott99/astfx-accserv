package com.daverj.account.service;

import com.daverj.account.config.KeycloakSecurityUtil;
import com.daverj.account.dto.mapper.AccountMapper;
import com.daverj.account.dto.request.AccountRegistryDTO;
import com.daverj.account.dto.response.AccountDTO;
import com.daverj.account.repository.AccountRepository;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    @Value("${keycloak.realm}")
    private String realm;
    private final KeycloakSecurityUtil keycloakUtil;

    public Page<AccountDTO> list(Pageable pageable) {
        log.info("Listing all accounts");
        return accountRepository.findAll(pageable)
                .map(accountMapper::toDto);
    }

    public AccountDTO findById(String id) {
        return accountRepository.findById(id)
                .map(accountMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }


    public AccountDTO findByEmail(String email) {
        return accountRepository.findByEmail(email)
                .map(accountMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Transactional
    public AccountDTO create(AccountRegistryDTO accountRegistryDTO) {
        UserRepresentation userRep = mapUserRep(accountRegistryDTO);
        Keycloak keycloak = keycloakUtil.getKeycloakInstance();
        Response response = keycloak.realm(realm).users().create(userRep);

        String userId = CreatedResponseUtil.getCreatedId(response);

        UserResource userResource = keycloak.realm(realm).users().get(userId);

        RoleRepresentation testerRealmRole = keycloak.realm(realm).roles()
                .get("CUSTOMER").toRepresentation();

        userResource.roles().realmLevel()
                .add(Arrays.asList(testerRealmRole));

        //userResource.sendVerifyEmail();

        accountRegistryDTO.setId(userId);

        return accountMapper.toDto(accountRepository.save(accountMapper.toEntityRegister(accountRegistryDTO)));
    }

    private UserRepresentation mapUserRep(AccountRegistryDTO account) {
        UserRepresentation userRep = new UserRepresentation();
        Random rand = new Random();
        userRep.setUsername("Customer-"+rand.nextInt(1000000));
        userRep.setEmail(account.getEmail());
        userRep.setEnabled(true);
        userRep.setEmailVerified(false);
        List<CredentialRepresentation> creds = new ArrayList<>();
        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setTemporary(false);
        cred.setValue(account.getPassword());
        creds.add(cred);
        userRep.setCredentials(creds);
        return userRep;
    }

}