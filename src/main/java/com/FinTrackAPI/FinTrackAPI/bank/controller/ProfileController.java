package com.FinTrackAPI.FinTrackAPI.bank.controller;

import com.FinTrackAPI.FinTrackAPI.bank.model.dto.ProfileCreateRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.model.dto.ProfileDeleteRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.model.entity.ProfileEntity;
import com.FinTrackAPI.FinTrackAPI.bank.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<?> createProfile(@RequestBody ProfileCreateRequestDto profileXCreateRequestDto) {
        var result = profileService.create(profileXCreateRequestDto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProfile(@RequestBody ProfileDeleteRequestDto profileDeleteRequestDto) {
        ProfileEntity response = profileService.deleteProfile(profileDeleteRequestDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
