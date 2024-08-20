package com.FinTrackAPI.FinTrackAPI.bank.controller;

import com.FinTrackAPI.FinTrackAPI.bank.model.dto.TransactionRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.service.TransactionService;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankController {

    private TransactionService transactionService;

    @GetMapping("/extract")
    public void extract() {

    }

    @PostMapping("/transaction")
    public void transaction(TransactionRequestDto transactionRequestDto) throws BadRequestException {

        transactionService.transaction(transactionRequestDto);
    }

    @GetMapping("/extract/period")
    public void extractByPeriod() {

    }
}
