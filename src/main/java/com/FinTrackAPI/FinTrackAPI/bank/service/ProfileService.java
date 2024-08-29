package com.FinTrackAPI.FinTrackAPI.bank.service;

import com.FinTrackAPI.FinTrackAPI.bank.model.dto.ProfileCreateRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.model.dto.ProfileDeleteRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.model.entity.ProfileEntity;
import com.FinTrackAPI.FinTrackAPI.bank.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ProfileEntity create(ProfileCreateRequestDto profileCreateRequestDto) {

        String encodedPassword = passwordEncoder.encode(profileCreateRequestDto.getPassword());

        ProfileEntity profile = ProfileEntity
                .builder()
                .username(profileCreateRequestDto.getUsername())
                .password(encodedPassword)
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
