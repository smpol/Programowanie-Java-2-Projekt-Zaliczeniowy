package com.mprzys.projekt.repository;

import com.mprzys.projekt.database.AppUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserProfileRepository extends JpaRepository<AppUserProfile, Long> {

}
