package com.FinTrackAPI.FinTrackAPI.unit;

import com.FinTrackAPI.FinTrackAPI.bank.model.dto.ProfileCreateRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.repository.ProfileRepository;
import com.FinTrackAPI.FinTrackAPI.bank.service.AuthService;
import com.FinTrackAPI.FinTrackAPI.bank.service.ProfileService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthTest {

    @Mock
    private AuthService authService;

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void successfulRecorderTest() throws BadRequestException {
        when(passwordEncoder.encode("test123")).thenReturn("encodedPassword");

        var requestCreateUser = ProfileCreateRequestDto
                .builder()
                .username("test")
                .password("test123")
                .build();

        profileService.create(requestCreateUser);

        // Simula o comportamento do authService
        when(authService.authenticate("test", "test123")).thenReturn(true);

        var response = authService.authenticate("test", "test123");

        assertTrue(response);
    }

    @Test
    public void registerTestWithError() throws BadRequestException {

        when(passwordEncoder.encode("test123")).thenReturn("encodedPassword");

        var requestCreateUser = ProfileCreateRequestDto
                .builder()
                .username("test")
                .password("test123")
                .build();

        profileService.create(requestCreateUser);


        when(authService.authenticate("test", "test")).thenReturn(false);

        var response = authService.authenticate("test", "test");

        assertFalse(response);
    }
}

