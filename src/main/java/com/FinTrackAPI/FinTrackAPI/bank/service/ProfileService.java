package com.FinTrackAPI.FinTrackAPI.bank.service;

import com.FinTrackAPI.FinTrackAPI.bank.model.dto.ProfileCreateRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.model.dto.ProfileChangeStatusRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.model.entity.ProfileEntity;
import com.FinTrackAPI.FinTrackAPI.bank.repository.ProfileRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.directory.InvalidAttributesException;

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

    public ProfileEntity changeStatusProfile(ProfileChangeStatusRequestDto profileChangeStatusRequestDto)
            throws InvalidAttributesException, BadRequestException {

        if(profileChangeStatusRequestDto.getStatus() == null ||
                (!profileChangeStatusRequestDto.getStatus().toLowerCase().equals("a") &&
                        !profileChangeStatusRequestDto.getStatus().toLowerCase().equals("e") &&
                        !profileChangeStatusRequestDto.getStatus().toLowerCase().equals("i")
                )) {
            throw new InvalidAttributesException("Invalid Status! Please send ('A', 'E', 'I')");
        }
            ProfileEntity profile = profileRepository.findByUsername(profileChangeStatusRequestDto.getUsername());

        if(profile != null) {
            profile.setStatus(profileChangeStatusRequestDto.getStatus());
        }
        else {
            throw new BadRequestException("Not found User with this username");
        }

        return profileRepository.save(profile);
    }


}
