package com.daverj.account.controller;

import com.daverj.account.dto.request.ProfileCreateDTO;
import com.daverj.account.dto.response.ProfileDTO;
import com.daverj.account.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("account/profile")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<Set<ProfileDTO>> profilesByAccount(@RequestParam String accountId) {
        return ResponseEntity.ok(profileService.profilesByAccount(accountId.toString()));
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody ProfileCreateDTO profileCreateDTO) {
        return new ResponseEntity<>(profileService.create(profileCreateDTO), HttpStatus.CREATED);
    }

}
