package com.daverj.account.dto.request;

import com.daverj.account.model.Account;
import com.daverj.account.model.enums.StreamingPlan;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@JsonPropertyOrder(value = {"email", "password", "streamingPlan"})
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountRegistryDTO implements Serializable {

    private String id;

    @NotBlank(message = "The field 'email' is mandatory")
    @JsonProperty(value = "email")
    private String email;

    @NotBlank(message = "The field 'password' is mandatory")
    @JsonProperty(value = "password")
    @Size(min = 6, message = "Should be high than 6 characters")
    private String password;


    private StreamingPlan streamingPlan;

    public AccountRegistryDTO(Account account) {
        id = account.getId();
        email = account.getEmail();
        streamingPlan = account.getStreamingPlan();
    }


}