package com.FinTrackAPI.FinTrackAPI.bank.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "history_entity")
public class HistoryEntity {

    @Id
    private Integer id;
    private String description;
    private Date createdAt;
    private String type;
    private Double value;
}
