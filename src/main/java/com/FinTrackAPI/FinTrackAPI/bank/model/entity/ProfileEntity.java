package com.FinTrackAPI.FinTrackAPI.bank.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profile_entity")
public class ProfileEntity {

    @Id
    private ObjectId id;
    private String name;
    private Double balance;
}
