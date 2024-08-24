package com.FinTrackAPI.FinTrackAPI.bank.controller;

import com.FinTrackAPI.FinTrackAPI.bank.model.dto.TransactionRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.model.dto.FindTransactionsRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.service.TransactionService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/extract")
    public void extract() {

    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody TransactionRequestDto transactionRequestDto) throws BadRequestException {

        return transactionService.deposit(transactionRequestDto);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody TransactionRequestDto transactionRequestDto) throws BadRequestException {

        return transactionService.withdraw(transactionRequestDto);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findByPeriod(@RequestBody FindTransactionsRequestDto findTransactionsRequestDto) {

       return transactionService.findTransactionsByDate(findTransactionsRequestDto);
    }
}
