package com.FinTrackAPI.FinTrackAPI.bank.service;

import com.FinTrackAPI.FinTrackAPI.bank.model.dto.TransactionRequestDto;
import com.FinTrackAPI.FinTrackAPI.bank.model.entity.ProfileEntity;
import com.FinTrackAPI.FinTrackAPI.bank.model.entity.TransactionEntity;
import com.FinTrackAPI.FinTrackAPI.bank.repository.ProfileRepository;
import com.FinTrackAPI.FinTrackAPI.bank.repository.TransactionRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public void transaction(TransactionRequestDto transactionRequestDto) throws BadRequestException {

        Optional<ProfileEntity> profile = profileRepository.findByName(transactionRequestDto.getPerson());

        if(profile.isPresent()) {
            TransactionEntity transaction = TransactionEntity
                    .builder()
                    .value(transactionRequestDto.getValue())
                    .profile(profile.get().getId())
                    .build();

            transactionRepository.save(transaction);
        }
        else {
            throw new BadRequestException("Não Encontrado Profile");
        }

    }
}
