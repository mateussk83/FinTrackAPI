package com.FinTrackAPI.FinTrackAPI.bank.controller;

import com.FinTrackAPI.FinTrackAPI.bank.model.dto.ProfileCreateRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.model.dto.ProfileChangeStatusRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.model.entity.ProfileEntity;
import com.FinTrackAPI.FinTrackAPI.bank.service.ProfileService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.InvalidAttributesException;

@RestController
@RequestMapping("profile")
public class  ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<?> createProfile(@RequestBody ProfileCreateRequestDto profileCreateRequestDto) {
        var result = profileService.create(profileCreateRequestDto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/change-status")
    public ResponseEntity<?> deleteProfile(@RequestBody ProfileChangeStatusRequestDto profileChangeStatusRequestDto)
            throws InvalidAttributesException, BadRequestException {

        ProfileEntity response = profileService.changeStatusProfile(profileChangeStatusRequestDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
