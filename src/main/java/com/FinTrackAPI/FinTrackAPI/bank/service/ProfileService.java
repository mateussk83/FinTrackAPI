package com.FinTrackAPI.FinTrackAPI.bank.service;

import com.FinTrackAPI.FinTrackAPI.bank.model.dto.ProfileCreateRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.model.dto.ProfileDeleteRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.model.entity.ProfileEntity;
import com.FinTrackAPI.FinTrackAPI.bank.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileEntity create(ProfileCreateRequestDto profileXCreateRequestDto) {

        ProfileEntity profile = ProfileEntity
                .builder()
                .username(profileXCreateRequestDto.getUsername())
                .balance(0.00)
                .build();

        return profileRepository.save(profile);
    }

    public ProfileEntity deleteProfile(ProfileDeleteRequestDto profileDeleteRequestDto) {

        ProfileEntity profile = profileRepository.findByUsername(profileDeleteRequestDto.getUsername());

        if(profile != null || profile.getStatus().equals("A")) {
            profile.setStatus("E");
        }

        return profileRepository.save(profile);
    }


}
