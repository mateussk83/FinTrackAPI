package com.FinTrackAPI.FinTrackAPI.bank.controller;

import com.FinTrackAPI.FinTrackAPI.bank.model.dto.ProfileRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody ProfileRequestDto profileRequestDto) {
        return profileService.create(profileRequestDto);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProfile(@RequestBody ProfileRequestDto profileRequestDto) {
        return profileService.deleteProfile(profileRequestDto);
    }
}
