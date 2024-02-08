package com.daverj.account.dto.response;

import com.daverj.account.model.Account;
import com.daverj.account.model.enums.StreamingPlan;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder(value = {"id", "email", "streamingPlan"})
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountDTO {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "streamingPlan")
    private StreamingPlan streamingPlan;

    public AccountDTO(Account account) {
        id = account.getId();
        email = account.getEmail();
        streamingPlan = account.getStreamingPlan();
    }

}