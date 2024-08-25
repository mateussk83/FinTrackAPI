package com.FinTrackAPI.FinTrackAPI.bank.service;

import com.FinTrackAPI.FinTrackAPI.bank.model.entity.ProfileEntity;
import com.FinTrackAPI.FinTrackAPI.bank.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticate(String username, String password) {
        ProfileEntity profile = profileRepository.findByUsername(username);
        if (profile != null) {
            return passwordEncoder.matches(password, profile.getPassword());
        }
        return false;
    }

}
