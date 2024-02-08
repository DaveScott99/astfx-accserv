package com.daverj.account.dto.mapper;

import com.daverj.account.dto.request.ProfileCreateDTO;
import com.daverj.account.dto.response.ProfileDTO;
import com.daverj.account.model.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public ProfileDTO toDto(Profile profile) {

        if (profile == null)
            return null;

        return new ProfileDTO(profile);

    }

    public Profile toEntityRegister(ProfileCreateDTO profileCreateDTO) {

        if (profileCreateDTO == null)
            return null;

        Profile entity = new Profile();

        entity.setId(profileCreateDTO.getId());
        entity.setName(profileCreateDTO.getName());
        entity.setIcon(profileCreateDTO.getIcon());
        entity.setAccount(profileCreateDTO.getAccount());

        return entity;

    }

}