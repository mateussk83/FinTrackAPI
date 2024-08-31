package com.FinTrackAPI.FinTrackAPI.bank.model.dto;

import lombok.Data;

@Data
public class ProfileChangeStatusRequestDto {

    private String username;
    private String status;

}
