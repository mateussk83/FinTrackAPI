package com.FinTrackAPI.FinTrackAPI.bank.service;

import com.FinTrackAPI.FinTrackAPI.bank.model.dto.ProfileRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.model.entity.ProfileEntity;
import com.FinTrackAPI.FinTrackAPI.bank.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ResponseEntity<ProfileEntity> create(ProfileRequestDto profileRequestDto) {

        ProfileEntity profile = ProfileEntity
                .builder()
                .name(profileRequestDto.getName())
                .balance(0.00)
                .build();

        profile = profileRepository.save(profile);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    public ResponseEntity<ProfileEntity> deleteProfile(ProfileRequestDto profileRequestDto) {

        ProfileEntity profile = profileRepository.findByName(profileRequestDto.getName());

        if(profile != null || profile.getStatus().equals("A")) {
            profile.setStatus("E");
        }
        profile = profileRepository.save(profile);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

}
