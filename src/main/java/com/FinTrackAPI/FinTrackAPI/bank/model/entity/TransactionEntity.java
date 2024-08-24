package com.FinTrackAPI.FinTrackAPI.bank.model.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatusCode;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transaction_entity")
public class TransactionEntity {

    @Id
    private ObjectId id;
    private Double value;
    private ObjectId profile;
    private String description;
    private String type;
    private Date createdAt;
}
