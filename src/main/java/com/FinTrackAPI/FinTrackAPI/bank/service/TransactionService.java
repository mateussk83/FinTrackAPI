package com.FinTrackAPI.FinTrackAPI.bank.service;

import com.FinTrackAPI.FinTrackAPI.bank.model.constants.TypeTransactionConstants;
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
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public ResponseEntity<String> deposit(TransactionRequestDto transactionRequestDto) throws BadRequestException {

        ProfileEntity profile = profileRepository.findByUsername(transactionRequestDto.getProfile());

        if(profile != null) {
            TransactionEntity transaction = TransactionEntity
                    .builder()
                    .value(transactionRequestDto.getValue())
                    .profile(profile.getId())
                    .description(transactionRequestDto.getDescription())
                    .type(TypeTransactionConstants.DEPOSIT)
                    .createdAt(new Date())
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

        ProfileEntity profile = profileRepository.findByUsername(transactionRequestDto.getProfile());

        if(profile != null) {

            if(profile.getBalance() > transactionRequestDto.getValue()) {
                profile.setBalance(profile.getBalance() - transactionRequestDto.getValue());
            }
            else {
                throw new BadRequestException("The withdrawal amount exceeds the account balance.");
            }

            TransactionEntity transaction = TransactionEntity
                    .builder()
                    .value(transactionRequestDto.getValue())
                    .description(transactionRequestDto.getDescription())
                    .profile(profile.getId())
                    .type(TypeTransactionConstants.WITHDRAW)
                    .createdAt(new Date())
                    .build();

            transactionRepository.save(transaction);
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
        ProfileEntity profile = profileRepository.findByUsername(findTransactionsRequestDto.getProfile());

        if(profile == null) {
            throw new IllegalArgumentException("Invalid Profile");
        }

        LocalDateTime startDateTime;
        LocalDateTime finalDateTime;

        if(findTransactionsRequestDto.getStartDate() == null
                && findTransactionsRequestDto.getFinalDate() == null) {
            startDateTime = LocalDate.now().minusMonths(3).atStartOfDay();
            finalDateTime = LocalDate.now().atStartOfDay().plusDays(1);
        } else {
            startDateTime = DateUtils.convertToLocalDateTime(
                    DateUtils.validateAndConvertDate(findTransactionsRequestDto.getStartDate())
            );
            finalDateTime = DateUtils.convertToLocalDateTime(
                    DateUtils.validateAndConvertDate(findTransactionsRequestDto.getFinalDate())
            );
        }

        List<TransactionEntity> transactionList =
                transactionRepository.findByCreatedAtBetweenAndProfile(startDateTime, finalDateTime, profile.getId());

        List<TransactionResponseDto> responseList = new ArrayList<>();

        for(TransactionEntity transaction: transactionList) {

            TransactionResponseDto response = TransactionResponseDto
                    .builder()
                    .value("R$: " + transaction.getValue())
                    .description(transaction.getDescription())
                    .type(transaction.getType())
                    .profile(profile.getUsername())
                    .build();

            responseList.add(response);
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
