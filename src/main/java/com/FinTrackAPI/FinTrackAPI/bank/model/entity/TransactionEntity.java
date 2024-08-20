package com.FinTrackAPI.FinTrackAPI.bank.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    private Integer id;
    private Double value;
    private Integer profile;
    private Date createdAt;
    private Date updateAt;
}
