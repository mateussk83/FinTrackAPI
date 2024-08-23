package com.FinTrackAPI.FinTrackAPI.bank.repository;


import com.FinTrackAPI.FinTrackAPI.bank.model.entity.TransactionEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends MongoRepository<TransactionEntity, Integer> {

    List<TransactionEntity> findByCreatedAtBetweenAndProfile(LocalDate startDate, LocalDate finalDate, ObjectId profile);
}
