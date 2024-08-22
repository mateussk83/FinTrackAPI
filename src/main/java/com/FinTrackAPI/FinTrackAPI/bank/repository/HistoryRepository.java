package com.FinTrackAPI.FinTrackAPI.bank.repository;

import com.FinTrackAPI.FinTrackAPI.bank.model.entity.HistoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends MongoRepository<HistoryEntity, Integer> {
}
