package com.FinTrackAPI.FinTrackAPI.bank.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transaction_entity")
public class TransactionEntity {

    @Id
    private Integer id;
    private Double value;
    private Integer profile;
    private Date createdAt;
    private Date updateAt;
}
