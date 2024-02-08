package com.daverj.account.service;

import com.daverj.account.dto.mapper.ProfileMapper;
import com.daverj.account.dto.request.ProfileCreateDTO;
import com.daverj.account.dto.response.ProfileDTO;
import com.daverj.account.model.Profile;
import com.daverj.account.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public Page<ProfileDTO> list(Pageable pageable) {
        log.info("Listing all profiles");
        return profileRepository.findAll(pageable)
                .map(profileMapper::toDto);
    }

    public Set<ProfileDTO> profilesByAccount(String accountId) {
        return profileRepository.findProfilesByAccountId(accountId)
                .stream()
                .map(profileMapper::toDto)
                .collect(Collectors.toSet());
    }

    public Profile findById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public ProfileDTO create(ProfileCreateDTO profile) {
        return profileMapper.toDto(profileRepository.save(profileMapper.toEntityRegister(profile)));
    }


}