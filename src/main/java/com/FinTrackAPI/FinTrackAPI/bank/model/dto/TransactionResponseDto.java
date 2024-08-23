package com.FinTrackAPI.FinTrackAPI.bank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {

    private String profile;
    private String value;
    private String description;
    private String type;
}
