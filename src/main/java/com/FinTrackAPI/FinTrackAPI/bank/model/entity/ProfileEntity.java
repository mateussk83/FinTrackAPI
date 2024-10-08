package com.FinTrackAPI.FinTrackAPI.bank.model.entity;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profile_entity")
public class ProfileEntity {

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    private String password;
    private String status = "A";
    private Double balance;
}
