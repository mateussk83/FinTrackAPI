package com.FinTrackAPI.FinTrackAPI.bank.repository;


import com.FinTrackAPI.FinTrackAPI.bank.model.entity.TransactionEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends MongoRepository<TransactionEntity, Integer> {

    List<TransactionEntity> findByCreatedAtBetweenAndProfile(LocalDateTime startDate, LocalDateTime finalDate, ObjectId profile);
}
