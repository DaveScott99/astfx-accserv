package com.daverj.account.controller;

import com.daverj.account.dto.request.AccountRegistryDTO;
import com.daverj.account.dto.response.AccountDTO;
import com.daverj.account.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/find")
    public ResponseEntity<AccountDTO> findById(@RequestParam String id) {
        return ResponseEntity.ok(accountService.findById(id.toString()));
    }

    @GetMapping
    public ResponseEntity<AccountDTO> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(accountService.findByEmail(email));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<AccountDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(accountService.list(pageable));
    }

    @PostMapping
    public ResponseEntity<AccountDTO> create(@Valid @RequestBody AccountRegistryDTO customerRegistryDTO) {
        return new ResponseEntity<>(accountService.create(customerRegistryDTO), HttpStatus.CREATED);
    }

}
