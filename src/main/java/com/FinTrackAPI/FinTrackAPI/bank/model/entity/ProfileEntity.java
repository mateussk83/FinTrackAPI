package com.FinTrackAPI.FinTrackAPI.bank.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
public class ProfileEntity {
    private Integer id;
    private String name;
    private Double Balance;
}
