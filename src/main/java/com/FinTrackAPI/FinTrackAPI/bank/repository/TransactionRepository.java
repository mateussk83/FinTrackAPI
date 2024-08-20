package com.FinTrackAPI.FinTrackAPI.bank.repository;


import com.FinTrackAPI.FinTrackAPI.bank.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

}
