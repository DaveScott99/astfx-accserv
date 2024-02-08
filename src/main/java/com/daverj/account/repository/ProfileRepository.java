package com.daverj.account.repository;

import com.daverj.account.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Set<Profile> findProfilesByAccountId(String accountId);

}
