package com.FinTrackAPI.FinTrackAPI.bank.model.dto;

import lombok.Data;

@Data
public class FindTransactionsRequestDto {

    private String startDate;
    private String finalDate;
    private String profile;
}
