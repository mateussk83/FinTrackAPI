package com.FinTrackAPI.FinTrackAPI.bank.repository;

import com.FinTrackAPI.FinTrackAPI.bank.model.entity.ProfileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfileRepository extends MongoRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByUsername(String username);
}
