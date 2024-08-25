package com.FinTrackAPI.FinTrackAPI.bank.controller;

import com.FinTrackAPI.FinTrackAPI.bank.model.dto.LoginRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto loginRequestdto) {
        boolean authenticated = authService.authenticate(loginRequestdto.getUsername(), loginRequestdto.getPassword());
        if (authenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


}
