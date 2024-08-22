package com.FinTrackAPI.FinTrackAPI.bank.repository;


import com.FinTrackAPI.FinTrackAPI.bank.model.entity.TransactionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionEntity, Integer> {

}
