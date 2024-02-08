package com.daverj.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "The field 'name' is mandatory")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "The field 'icon' is mandatory")
    @Column(nullable = false)
    private String icon;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnoreProperties("streamingPlan")
    private Account account;


}