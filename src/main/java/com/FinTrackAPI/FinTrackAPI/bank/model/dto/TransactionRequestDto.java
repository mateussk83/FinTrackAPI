package com.FinTrackAPI.FinTrackAPI.bank.model.dto;

import lombok.Data;

@Data
public class TransactionRequestDto {

    private String description;
    private String type;
    private Double value;
    private String person;
}
