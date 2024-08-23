package com.FinTrackAPI.FinTrackAPI.bank.service;

import com.FinTrackAPI.FinTrackAPI.bank.model.TypeTransactionConstants;
import com.FinTrackAPI.FinTrackAPI.bank.model.dto.TransactionRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.model.dto.TransactionResponseDto;
import com.FinTrackAPI.FinTrackAPI.bank.model.entity.ProfileEntity;
import com.FinTrackAPI.FinTrackAPI.bank.model.entity.TransactionEntity;
import com.FinTrackAPI.FinTrackAPI.bank.model.dto.FindTransactionsRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.repository.ProfileRepository;
import com.FinTrackAPI.FinTrackAPI.bank.repository.TransactionRepository;
import com.FinTrackAPI.FinTrackAPI.bank.utils.DateUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public ResponseEntity<String> deposit(TransactionRequestDto transactionRequestDto) throws BadRequestException {

        ProfileEntity profile = profileRepository.findByName(transactionRequestDto.getPerson());

        if(profile != null) {
            TransactionEntity transaction = TransactionEntity
                    .builder()
                    .value(transactionRequestDto.getValue())
                    .profile(profile.getId())
                    .type(TypeTransactionConstants.DEPOSIT)
                    .build();

            transactionRepository.save(transaction);
            profile.setBalance(profile.getBalance() + transactionRequestDto.getValue());
            profileRepository.save(profile);

            DecimalFormat df = new DecimalFormat("#.##");
            return new ResponseEntity<>("R$: " + df.format(profile.getBalance()), HttpStatus.OK);
        }
        else {
            throw new BadRequestException("Não Encontrado Profile");
        }
    }

    public ResponseEntity<String> withdraw(TransactionRequestDto transactionRequestDto) throws BadRequestException {

        ProfileEntity profile = profileRepository.findByName(transactionRequestDto.getPerson());

        if(profile != null) {
            TransactionEntity transaction = TransactionEntity
                    .builder()
                    .value(transactionRequestDto.getValue())
                    .profile(profile.getId())
                    .type(TypeTransactionConstants.WITHDRAW)
                    .build();

            transactionRepository.save(transaction);
            if(profile.getBalance() > transactionRequestDto.getValue()) {
                profile.setBalance(profile.getBalance() - transactionRequestDto.getValue());
            }
            else {
                throw new BadRequestException("The withdrawal amount exceeds the account balance.");
            }
            profileRepository.save(profile);

            DecimalFormat df = new DecimalFormat("#.##");
            return new ResponseEntity<>("R$: " + df.format(profile.getBalance()), HttpStatus.OK);
        }
        else {
            throw new BadRequestException("Profile Don't Exists");
        }
    }

    public ResponseEntity<List<TransactionResponseDto>> findTransactionsByDate(FindTransactionsRequestDto findTransactionsRequestDto) {



        if(findTransactionsRequestDto == null || findTransactionsRequestDto.getProfile() == null) {
            throw new IllegalArgumentException("Invalid Request");
        }
        ProfileEntity profile = profileRepository.findByName(findTransactionsRequestDto.getProfile());

        if(profile == null) {
            throw new IllegalArgumentException("Invalid Profile");
        }

        LocalDate startDate;
        LocalDate finalDate;

        if(findTransactionsRequestDto.getStartDate() == null
                && findTransactionsRequestDto.getFinalDate() == null) {
            startDate = LocalDate.now().minus(3, ChronoUnit.MONTHS);
            finalDate = LocalDate.now();
        } else {
            startDate = DateUtils.validateAndConvertDate(findTransactionsRequestDto.getStartDate());
            finalDate = DateUtils.validateAndConvertDate(findTransactionsRequestDto.getFinalDate());
        }


        List<TransactionEntity> transactionList = transactionRepository.findByCreatedAtBetweenAndProfile(startDate, finalDate, profile.getId());

        List<TransactionResponseDto> responseList = new ArrayList<>();

        for(TransactionEntity transaction: transactionList) {

            TransactionResponseDto response = TransactionResponseDto
                    .builder()
                    .value("R$: " + transaction.getValue())
                    .description(transaction.getDescription())
                    .type(transaction.getType())
                    .build();

            responseList.add(response);
        }

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
