package com.FinTrackAPI.FinTrackAPI.bank.repository;

import com.FinTrackAPI.FinTrackAPI.bank.model.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByName(String name);
}
