package com.example.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Currencypair {

    public String id;
    public Long entryID;
    public Long key;
    public Integer quoteType;
    public LocalDateTime time;
    public Double bid;
    public Double ask;
    public Integer bidVolume;
    public Integer askVolume;
    public Integer depth;
    public Integer positionNumber;
    public String compID;
    public Long validTime;
}
