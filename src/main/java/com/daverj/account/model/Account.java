package com.daverj.account.model;

import com.daverj.account.model.enums.StreamingPlan;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(unique = true)
    private String email;

    private Integer streamingPlan;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private Set<Profile> profiles;

    public Account(String id, String email, StreamingPlan streamingPlan, Set<Profile> profiles) {
        this.id = id;
        this.email = email;
        setStreamingPlan(streamingPlan);
    }

    public void setStreamingPlan(StreamingPlan streamingPlan) {
        if (streamingPlan != null) {
            this.streamingPlan = streamingPlan.getCode();
        }
    }

    public StreamingPlan getStreamingPlan() {
        return StreamingPlan.valueOf(streamingPlan);
    }

}
