package com.daverj.account.dto.request;

import com.daverj.account.model.Account;
import com.daverj.account.model.Profile;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@JsonPropertyOrder(value = {"name", "icon", "account"})
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProfileCreateDTO implements Serializable {

    private Long id;

    @NotBlank(message = "The field 'name' is mandatory")
    @JsonProperty(value = "name")
    private String name;

    @NotBlank(message = "The field 'icon' is mandatory")
    @JsonProperty(value = "icon")
    private String icon;


    private Account account;

    public ProfileCreateDTO(Profile profile) {
        id = profile.getId();
        name = profile.getName();
        icon = profile.getIcon();
        account = profile.getAccount();
    }

}