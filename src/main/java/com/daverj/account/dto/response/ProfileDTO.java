package com.daverj.account.dto.response;

import com.daverj.account.model.Profile;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder(value = {"id", "name", "icon"})
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProfileDTO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "icon")
    private String icon;

    public ProfileDTO(Profile profile) {
        id = profile.getId();
        name = profile.getName();
        icon = profile.getIcon();
    }

}